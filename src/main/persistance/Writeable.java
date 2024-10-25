// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

package persistance;

import org.json.JSONObject;

// Interface with method that returns as JSON object
public interface Writeable {
    
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
    
}
