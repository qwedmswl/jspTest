package kr.or.ddit.fileUpload;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUploadUtilTest {

	private static final Logger logger = LoggerFactory.getLogger(FileUploadUtilTest.class);
	@Test
	public void getFilenameTest() {
		/***Given***/
		String contentDisposition = "form-data; name=\"img\"; filename=\"moon.png\"";
		
		/***When***/
		String fileName = FileUploadUtil.getFilename(contentDisposition);
		/***Then***/
		assertEquals("moon.png", fileName);
	}

	@Test
	public void getExtensionFailTest() {
		/***Given***/
		String filename = "sally";

		/***When***/
		String ext = FileUploadUtil.getExtension(filename);
		
		/***Then***/
		assertEquals("", ext);
		
	}
}
