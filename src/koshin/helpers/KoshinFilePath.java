package koshin.helpers;

import java.nio.file.Path;

public class KoshinFilePath {
            
}


/*

    We have exactly three nodes, namely:
    - Default
    - Custom
    - Distribution

    Under each node is a similar file/directory structure
    - similar, but but generally NOT identical



    Methods required:

    - Given a file in a node, is the equivalent file in another node:
    - - Not there; or
    - - There but different; or 
    - - There and identical? 

    - Given a file in a node, copy it to another node
    - - must copy to the equivalent place in other node
    - - Might override an old file; might not
    - - (never copy FROM Dist)
    - - (only copy TO Dist)

    - Given a file in a node, delete it
    - - (only delete from Dist)

    - Given a file in a node
    - - return its FULL filepath

    - Given a file in a node
    - - return its relative filepath from the node



*/
