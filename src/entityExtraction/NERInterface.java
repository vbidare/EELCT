package entityExtraction;
import java.util.ArrayList;

import main.Data;
import main.Entity;
/*
 *  Created by veerendra on 11/6/15.
 *  An interface for Named Entity Recognition. All NERs will implement this interface.
 */
public interface NERInterface {
    ArrayList<Entity> getEntities(Data dataObject);
}