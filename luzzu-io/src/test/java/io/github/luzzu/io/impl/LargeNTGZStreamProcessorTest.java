package io.github.luzzu.io.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import io.github.luzzu.exceptions.LuzzuIOException;
import io.github.luzzu.io.IOProcessor;
import io.github.luzzu.io.impl.LargeNTGZStreamProcessor;
import io.github.luzzu.semantics.commons.ResourceCommons;
import io.github.luzzu.semantics.vocabularies.LMI;

public class LargeNTGZStreamProcessorTest extends Assert {
	
	private IOProcessor processor = null;
	
	private String datasetPLD = "http://example.org";
	private String datasetLocation = this.getClass().getClassLoader().getResource("processor-datadumps/test-data-40377.nt.gz").toExternalForm();
	private Boolean generateReport = false;
	private Model metricConf = ModelFactory.createDefaultModel();
	
	@Before
	public void setUp() throws Exception {
		
		metricConf.add(metricConf.createStatement(ResourceCommons.generateURI(), LMI.metric, "io.github.luzzu.testing.metrics.SimpleCountMetric"));
		processor = new LargeNTGZStreamProcessor(datasetPLD, datasetLocation, generateReport, metricConf);
	}
	
	@After
	public void tearDown() throws Exception{}
	
	@Test
	public void setUpProcessTest() throws LuzzuIOException {
		processor.setUpProcess();
		assertEquals(1, processor.getNumberOfInitMetrics());
	}
	
	@Test
	public void processingTest() {
		try {
			processor.setUpProcess();
			processor.startProcessing();
		} catch (LuzzuIOException | InterruptedException e) {
			e.printStackTrace();
		}
		
		long triplesProcessed = 0;
		try {
			triplesProcessed = processor.getIOStats().get(0).getTriplesProcessed();
		} catch (LuzzuIOException e) {
			e.printStackTrace();
		}
		long expectedTriplesProcessed = 40377l;
		
		assertEquals(expectedTriplesProcessed, triplesProcessed);
	}
	
	@Test
	public void getPartialSubsetTest() throws IOException {
		InputStream reader = ((LargeNTGZStreamProcessor)processor).getPartialSubset(1000l, 2000l);
		
		int excepted = 1001;
		int actual = IOUtils.readLines(reader, Charset.defaultCharset()).size();

		assertEquals(excepted, actual);
	}
	
}
