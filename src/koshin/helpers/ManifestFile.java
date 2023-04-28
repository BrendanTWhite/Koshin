package koshin.helpers;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ManifestFile {
   private Path manifestFilePath; 
    public ManifestFile (Path manifestFilePath) throws Exception {
        
        if (manifestFilePath.getFileName().toString().equals("ManifestFile.txt")) {
            this.manifestFilePath = manifestFilePath;
        } else {
            throw new Exception("This is not a manifest file");
       }
        
    }
    
        public List<Path> getAllDecendants() throws IOException {
        List<Path> result;
        try (
                Stream<Path> pathStream = Files.find(this.manifestFilePath.getParent(),
                Integer.MAX_VALUE,
                (p, basicFileAttributes) -> ! Files.isDirectory(p))
        ) 
        {
            result = pathStream.collect(Collectors.toList());
        }
        return result;
    }
    
}
