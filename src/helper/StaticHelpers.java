/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  functionalInterfaces.Object2DBuilder
 *  functionalInterfaces.Object2DBuilderReturn
 *  functionalInterfaces.Object3DBuilder
 *  functionalInterfaces.Object3DBuilderReturn
 *  functionalInterfaces.Selector
 *  org.apache.commons.math3.analysis.UnivariateFunction
 *  org.apache.commons.math3.analysis.interpolation.SplineInterpolator
 */
package helper;

import functionalInterfaces.Object2DBuilder;
import functionalInterfaces.Object2DBuilderReturn;
import functionalInterfaces.Object3DBuilder;
import functionalInterfaces.Object3DBuilderReturn;
import functionalInterfaces.Selector;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;
//import org.apache.commons.math3.analysis.UnivariateFunction;
//import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;

public class StaticHelpers {
    
    public static int[] parseIntegerArray(String s, int size) {
        int[] array;
        array = new int[size];
        Scanner scan = new Scanner(s);
        Throwable throwable = null;
        try {
            int n = 0;
            while (scan.hasNextInt()) {
                array[n] = scan.nextInt();
                ++n;
            }
        }
        catch (Throwable n) {
            throwable = n;
            throw n;
        }
        finally {
            if (scan != null) {
                if (throwable != null) {
                    try {
                        scan.close();
                    }
                    catch (Throwable n) {
                        throwable.addSuppressed(n);
                    }
                } else {
                    scan.close();
                }
            }
        }
        return array;
    }

    /*public static double interpole(double[] xArray, double[] yArray, double value) {
        SplineInterpolator interpolator = new SplineInterpolator();
        UnivariateFunction function = interpolator.interpolate(xArray, yArray);
        return function.value(value);
    }

    public static double interpole(List<Point> list, double value) {
        double[] xArray = new double[list.size()];
        double[] yArray = new double[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            xArray[i] = list.get((int)i).x;
            yArray[i] = list.get((int)i).y;
        }
        SplineInterpolator interpolator = new SplineInterpolator();
        UnivariateFunction function = interpolator.interpolate(xArray, yArray);
        return function.value(value);
    }*/

    public static <T> void iterateList(List<? extends T> list, Selector<T> selector) {
        if (list.size() < 100) {
            for (T o : list) {
                if (o == null) continue;
                selector.action(o);
            }
        } else {
            list.parallelStream().filter(o -> o != null).forEach(o -> {
                selector.action(o);
            });
        }
    }    
    
    public static <T> void iterateSet(Set<? extends T> set, Selector<T> selector){
        if(set.size() < 100){
            for (T o : set){
                if (o == null) continue;
                selector.action(o);
                    
            }
        } else {
            set.stream().filter(o -> o != null).forEach(o -> {
                selector.action(o);
            });
        }
    }

    public static void parse2DArray(Object[][] array, Selector selector) {
        int totalLength = array.length * array[0].length;
        if (totalLength < 200) {
            for (int a = 0; a < array.length; ++a) {
                for (int b = 0; b < array[a].length; ++b) {
                    Object o2 = array[a][b];
                    selector.action(o2);
                }
            }
        } else {
            ((Arrays.stream(array).parallel()).flatMap(a1 -> Arrays.stream(a1)).parallel()).forEach(o -> {
                selector.action(o);
            });
        }
    }

    public static <T> void parse3DArray(T[][][] array, Selector<T> selector) {
        int totalLength = array.length * array[0].length * array[0][0].length;
        if (totalLength < 200) {
            for (int a3 = 0; a3 < array.length; ++a3) {
                for (int b = 0; b < array[a3].length; ++b) {
                    for (int c = 0; c < array[a3][b].length; ++c) {
                        T o2 = array[a3][b][c];
                        selector.action(o2);
                    }
                }
            }
        } else {
            ((Arrays.stream(array).parallel()).flatMap(a -> (Arrays.stream(a).parallel()).flatMap(a2 -> Arrays.stream(a2).parallel())).parallel()).forEach(o -> {
                selector.action(o);
            });
        }
    }

    public static void build2DObject(int x, int y, Object2DBuilder builder) {
        int totalLength = x * y;
        if (totalLength < 200) {
            for (int a = 0; a < x; ++a) {
                for (int b = 0; b < y; ++b) {
                    builder.build2Dobject(a, b);
                }
            }
        } else {
            IntStream.range(0, x).parallel().forEach(a -> {
                IntStream.range(0, y).parallel().forEach(b -> {
                    builder.build2Dobject(a, b);
                });
            });
        }
    }

    public static void build3DObject(int x, int y, int z, Object3DBuilder builder) {
        int totalLength = x * y * z;
        if (totalLength < 200) {
            for (int a2 = 0; a2 < x; ++a2) {
                for (int b = 0; b < y; ++b) {
                    for (int c = 0; c < z; ++c) {
                        builder.build3Dobject(a2, b, c);
                    }
                }
            }
        } else {
            IntStream.range(0, x).parallel().forEach(a -> {
                IntStream.range(0, y).parallel().forEach(b -> {
                    IntStream.range(0, z).parallel().forEach(c -> {
                        builder.build3Dobject(a, b, c);
                    });
                });
            });
        }
    }

    public static void fill2DArray(Object[][] array, Object2DBuilderReturn builder) {
        int totalLength = array.length * array[0].length;
        if (totalLength < 200) {
            for (int x2 = 0; x2 < array.length; ++x2) {
                for (int y = 0; y < array[x2].length; ++y) {
                    array[x2][y] = builder.build2Dobject(x2, y);
                }
            }
        } else {
            ((Stream)IntStream.range(0, array.length).parallel().mapToObj(x -> (String[])((Stream)IntStream.range(0, array[x].length).parallel().mapToObj(y -> builder.build2Dobject(x, y)).parallel()).toArray(x$0 -> new String[x$0])).parallel()).toArray(x$0 -> new String[x$0][]);
        }
    }

    public static void fill3DArray(Object[][][] array, Object3DBuilderReturn builder) {
        int totalLength = array.length * array[0].length * array[0][0].length;
        if (totalLength < 200) {
            for (int x2 = 0; x2 < array.length; ++x2) {
                for (int y = 0; y < array[x2].length; ++y) {
                    for (int z = 0; z < array[x2][y].length; ++z) {
                        array[x2][y][z] = builder.build3Dobject(x2, y, z);
                    }
                }
            }
        } else {
            ((Stream)IntStream.range(0, array.length).parallel().mapToObj(x -> (String[][])((Stream)IntStream.range(0, array[x].length).parallel().mapToObj(y -> (String[])((Stream)IntStream.range(0, array[y].length).parallel().mapToObj(z -> builder.build3Dobject(x, y, z)).parallel()).toArray(x$0 -> new String[x$0])).parallel()).toArray(x$0 -> new String[x$0][])).parallel()).toArray(x$0 -> new String[x$0][][]);
        }
    }

    public static void printDebug(List<String> list) {
        list.stream().forEach(System.out::println);
    }

    public static boolean isIncluded(int i, int[] a) {
        int s = a[0];
        int e = a[a.length - 1];
        return i >= s && i <= e;
    }

    public static boolean anyObjectIsInCollections(Collection c1, Collection c2) {
        for (Object o : c1) {
            if (!c2.contains(o)) continue;
            return true;
        }
        return false;
    }
    
    public static String loadResource(String filename) throws Exception {
        String result;
        try (InputStream in = StaticHelpers.class.getClass().getResourceAsStream(filename)){
            Scanner scanner = new Scanner(in, "UTF-8");
            result = scanner.useDelimiter("\\A").next();
        }
        return result;                    
    }
    
    public static List<String> readAllLines(String filename) throws Exception {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(StaticHelpers.class.getClass().getResourceAsStream(filename)))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        }    
        return list;
    }
    
    public static float[] listToArray(List<Float> list){
        int size = list != null ? list.size() : 0;
        float[] floatArr = new float[size];
        for (int i = 0; i < size; i++){
            floatArr[i] = list.get(i);
        }
        return floatArr;
    }
}
