package persistence;


import org.json.JSONObject;

//this persistance package is heaviliy inspired by the example given to us

public interface Writeable {

    // EFFECTS: returns this as JSON object

    JSONObject toJson();
}
