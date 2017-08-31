package io.radanalytics.examples.vertx;

import javax.validation.constraints.NotNull;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkException;
import org.apache.spark.api.java.JavaSparkContext;

public class SparkContextProvider {

    private static SparkContextProvider INSTANCE = null;

    private SparkConf sparkConf;
    private JavaSparkContext sparkContext;

    private SparkContextProvider() {
        this.sparkConf = new SparkConf().setAppName("JavaSparkPi");
        this.sparkConf.setJars(new String[]{""});
        this.sparkContext = new JavaSparkContext(sparkConf);
    }

    public static boolean init() {
        try {
            if (INSTANCE == null) {
                INSTANCE = new SparkContextProvider();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @NotNull
    public static JavaSparkContext getContext() {
        return INSTANCE.sparkContext;
    }

}
