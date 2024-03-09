package persistence;


import org.json.JSONObject;

//this persistance package is heaviliy inspired by the example given to us

//contains abstract toJson method that the model classes must implement
public interface Writeable {

    // EFFECTS: returns this as JSON object

    JSONObject toJson();
}
