package com.huutho.photo;

import com.huutho.photo.crop.StateBitmapManager;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test() {

        StateBitmapManager manager = new StateBitmapManager();
        manager.appendConfig("A");
        manager.appendConfig("B");
        manager.appendConfig("C");
        manager.appendConfig("E");
        manager.appendConfig("F");


        System.out.print("\ncurrentIndex:" + manager.getCurrentIndexConfig() + "\t\t\t\t\tcurrentConfig:" + manager.getCurrentConfig());


        System.out.print("\ncurrentIndex:" + manager.getPreviousConfigAndSetCurrentPosition());
        System.out.print("\ncurrentIndex:" + manager.getCurrentIndexConfig() + "\t\t\t\t\tcurrentConfig:" + manager.getCurrentConfig());

        System.out.print("\ncurrentIndex:" + manager.getPreviousConfigAndSetCurrentPosition());
        System.out.print("\ncurrentIndex:" + manager.getCurrentIndexConfig() + "\t\t\t\t\tcurrentConfig:" + manager.getCurrentConfig());

    }
}