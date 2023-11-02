package cat.iesesteveterradas.mp06.uf1.exercicis;
import java.io.Serializable;
import java.util.HashMap;

public class PR131hashmap implements Serializable {
    private String basePath = System.getProperty("user.dir") + "/data/";
    private String filePath = basePath + "PR131HashMapData.ser";
    private HashMap<String, Integer> hMap;

    public PR131hashmap() {
        hMap = new HashMap<>();
    }

    public void appendInfoToMap(String key, int value) {
        hMap.put(key, value);
    }

    public String getFilePath() {
        return filePath;
    }

    public HashMap<String, Integer> gethMap() {
        return hMap;
    }

}
