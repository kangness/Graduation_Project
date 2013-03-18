package nl.surfnet.onelogin;

import java.util.zip.Deflater;

public class Util {

    public static String compress(byte[] input) {

	try {
	    // Compress the bytes
	    byte[] output = new byte[input.length + 100];
	    Deflater compresser = new Deflater();
	    compresser.setInput(input);
	    compresser.finish();
	    int compressedDataLength = compresser.deflate(output);

	    // Decode the bytes into a String
	    String outputString = new String(output, 0, compressedDataLength, "UTF-8");
	    return outputString;
	} catch (java.io.UnsupportedEncodingException ex) {
	    // handle
	}

	return null;

    }

}
