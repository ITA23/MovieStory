package ita23.moviestory.utilities;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * This utility class is intended to help with some odds about
 *  finding the right base-path.</p>
 * "base-path" in this context means the path, in which the .jar
 *  -file is executed.
 * @author Lukas Knuth
 * @version 1.0
 * @see <a href="http://stackoverflow.com/questions/320542">StackOverflow</a>
 */
public final class PathUtility {
    
    /** The cached results of {@code getBasePath()} */
    private static File base_path;

    /**
     * Get the base-path, which returns the path in which the ".jar"-
     *  file is placed.
     * @return the base path of this application.
     */
    public static File getBasePath(){
        try {
            String path = PathUtility.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            String decodedPath = URLDecoder.decode(path, "UTF-8");
            base_path = new File(decodedPath.substring(0, path.lastIndexOf("/") + 1));
            return base_path;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // Otherwise, use standard path:
        base_path = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
        return base_path;
    }
}
