package cat.iesesteveterradas.mp06.uf1.exercicis;
import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PR143GestioLlibreriaMain {

    public static void main(String[] args) throws IOException {
        PR143GestioLlibreriaMain gs = new PR143GestioLlibreriaMain();
        gs.writeJSON();
        gs.readJSON();
        gs.addEntryJSON();
        gs.readJSON();
        gs.deleteEntryJSON();
        gs.readJSON();
        gs.saveToJSON();

    }
     public void readJSON() throws IOException {
         ObjectMapper objectMapper = new ObjectMapper();

         List<Map<String, Object>> llistaDeLlibres = objectMapper.readValue(new File("data/llibres_input.json"),
                 objectMapper.getTypeFactory().constructCollectionType(List.class, Map.class));

         for (Map<String, Object> map : llistaDeLlibres ) {
             System.out.println(map);
         }
    }
    public void writeJSON() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> llistaDeLlibres = objectMapper.readValue(new File("data/llibres_input.json"),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Map.class));
        llistaDeLlibres.get(0).replace("any", 1995);
        objectMapper.writeValue(new File("data/llibres_input.json"), llistaDeLlibres);
        System.out.println("Dades guardades amb èxit a llibres_input.json!");
    }
    public void addEntryJSON() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        List<Map<String, Object>> maps = objectMapper.readValue(new File("data/llibres_input.json"),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Map.class));

        HashMap<String, Object> map = new LinkedHashMap<>();

        map.put("id", 4);
        map.put("títol", "Històries de la ciutat");
        map.put("autor", "Miquel Soler");
        map.put("any", 2022);

        maps.add(map);

        objectMapper.writeValue(new File("data/llibres_input.json"), maps);
        System.out.println("Dades guardades amb èxit a llibres_input.json!");
    }
    public void deleteEntryJSON() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        List<Map<String, Object>> maps = objectMapper.readValue(new File("data/llibres_input.json"),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Map.class));

        for (int i = 0; i < maps.size(); i++) {
            Map <String, Object> llibre = maps.get(i);
            if (llibre.get("id").equals(2)) {
                maps.remove(i);
                break;
            }
        }
        objectMapper.writeValue(new File("data/llibres_input.json"), maps);
        System.out.println("Llibre amb id 2 eliminat amb èxit a llibres_input.json!");
    }
    public void saveToJSON() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        List<Map<String, Object>> maps = objectMapper.readValue(new File("data/llibres_input.json"),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Map.class));

        objectMapper.writeValue(new File("data/llibres_output.json"), maps);
        System.out.println("Dades guardades a llibres_output.json!");
    }
}
