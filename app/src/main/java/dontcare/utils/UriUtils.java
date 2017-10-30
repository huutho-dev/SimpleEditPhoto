package dontcare.utils;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;


import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by ThoNh on 10/4/2017.
 */

public class UriUtils {
    private static final String TAG = UriUtils.class.getSimpleName();

    private static final String URI_AUTHORITY_EXTERNAL_DOCUMENT = "com.android.externalstorage.documents";          /*Uri authority is ExternalStorageProvider.*/
    private static final String URI_AUTHORITY_DOWNLOAD_DOCUMENT = "com.android.providers.downloads.documents";      /*Uri authority is DownloadsProvider.*/
    private static final String URI_AUTHORITY_MEDIA_DOCUMENT = "com.android.providers.media.documents";             /*Uri authority is MediaProvider.*/
    private static final String URI_AUTHORITY_GOOGLE_PHOTO_DOCUMENT = "com.google.android.apps.photos.content";     /*Uri authority is Google Photos.*/

    private static final String URI_TYPE_IMAGE = "image";
    private static final String URI_TYPE_VIDEO = "video";
    private static final String URI_TYPE_AUDIO = "audio";

    private static final String URI_SCHEMA_FILE = "file";
    private static final String URI_SCHEMA_CONTENT = "content";


    /**
     * file inside external storage (uri_data not yet in database)
     *
     * @param uri
     * @return Path of file with uri
     */
    private static String getPathFromUriExternalStorage(Uri uri) {
        final String docId = DocumentsContract.getDocumentId(uri);
        final String[] split = docId.split(":");
        final String type = split[0];

        if ("primary".equalsIgnoreCase(type)) {
            return Environment.getExternalStorageDirectory() + "/" + split[1];
        }
        return null;
    }


    /**
     * file inside download folder (uri_data not yet in database)
     *
     * @param uri
     * @return
     */
    private static String getPathFromUriDownload(Context context, Uri uri) {
        final String id = DocumentsContract.getDocumentId(uri);
        final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.parseLong(id));
        return getFilePathFromUri(context, contentUri, null, null);
    }


    /**
     * file inside database
     *
     * @param uri
     * @return
     */
    private static String getPathFromUriMedia(Context context, Uri uri) {
        final String docId = DocumentsContract.getDocumentId(uri);
        final String[] split = docId.split(":");
        final String uriType = split[0];

        Uri contentUri = null;
        switch (uriType) {
            case URI_TYPE_IMAGE:
                contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                break;
            case URI_TYPE_AUDIO:
                contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                break;
            case URI_TYPE_VIDEO:
                contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                break;
        }

        final String selection = MediaStore.MediaColumns._ID + "=?";
        final String[] selectionArgs = new String[]{split[1]};
        return getFilePathFromUri(context, contentUri, selection, selectionArgs);
    }


    private static String getPathFileFromUri(Context context, Uri uri) {
        boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {

            switch (uri.getAuthority().toLowerCase()) {
                case URI_AUTHORITY_EXTERNAL_DOCUMENT:
                    return getPathFromUriExternalStorage(uri);

                case URI_AUTHORITY_DOWNLOAD_DOCUMENT:
                    return getPathFromUriDownload(context, uri);

                case URI_AUTHORITY_MEDIA_DOCUMENT:
                    return getPathFromUriMedia(context, uri);
            }

        }

        // MediaStore (and general)
        else if (URI_SCHEMA_CONTENT.equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (uri.getAuthority().equalsIgnoreCase(URI_AUTHORITY_GOOGLE_PHOTO_DOCUMENT)) {
                return uri.getLastPathSegment();
            }

            return getFilePathFromUri(context, uri, null, null);

        } else if (URI_SCHEMA_FILE.equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    private static String getFilePathFromUri(Context context, Uri uri, String selection, String[] selectionArgs) {

        Cursor cursor = null;
        final String column = MediaStore.MediaColumns.DATA;
        final String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * Get path of file from Uri
     *
     * @param uri
     * @return AbsolutePath of File with Uri
     */
    public static String getAbsolutePathFromUri(Context context, final Uri uri) {
        Log.e(TAG, "Uri schema:" + uri.getScheme() + "\nUri auth:" + uri.getAuthority() + "\nUri path:" + uri.getPath());
        String filePath = getPathFileFromUri(context, uri);
        if (filePath != null) {
            return filePath;
        } else {
            final ParcelFileDescriptor parcelFileDescriptor;
            final FileDescriptor fileDescriptor;
            FileInputStream fileInputStream = null;
            try {
                parcelFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r");
                fileDescriptor = parcelFileDescriptor.getFileDescriptor();
                fileInputStream = new FileInputStream(fileDescriptor);
                File outputFile = new File(context.getCacheDir(), "temp_img.jpg");

                // write file
                FileOutputStream fileOutputStream = new FileOutputStream(outputFile.getAbsolutePath());
                byte[] buffer = new byte[5 * 1024];
                int n = -1;
                while ((n = fileInputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, n);
                }
                fileOutputStream.flush();
                fileOutputStream.close();

                return outputFile.getAbsolutePath();
            } catch (Exception e) {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                return null;
            }
        }
    }
}
