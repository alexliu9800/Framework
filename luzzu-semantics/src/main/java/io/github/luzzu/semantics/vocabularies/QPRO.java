/* CVS $Id: $ */
package io.github.luzzu.semantics.vocabularies; 
import org.apache.jena.rdf.model.*;
 
/**
 * Vocabulary definitions from /Users/jeremy/Documents/Codebase/Repository/Luzzu/luzzu-semantics/src/main/resources/vocabularies/qpro/qpro.ttl 
 * @author Auto-generated by schemagen on 19 Jan 2018 23:46 
 */
public class QPRO {
    /** <p>The RDF model that holds the vocabulary terms</p> */
    private static final Model M_MODEL = ModelFactory.createDefaultModel();
    
    /** <p>The namespace of the vocabulary as a string</p> */
    public static final String NS = "http://purl.org/eis/vocab/qpro#";
    
    /** <p>The namespace of the vocabulary as a string</p>
     * @return namespace as String
     * @see #NS */
    public static String getURI() {return NS;}
    
    /** <p>The namespace of the vocabulary as a resource</p> */
    public static final Resource NAMESPACE = M_MODEL.createResource( NS );
    
    /** <p>Represents the dataset URI on which quality metrics where computed</p> */
    public static final Property computedOn = M_MODEL.createProperty( "http://purl.org/eis/vocab/qpro#computedOn" );
    
    /** <p>Describes a particular exception raised by the Resource or Triple in question, 
     *  causing a quality problem</p>
     */
    public static final Property exceptionDescription = M_MODEL.createProperty( "http://purl.org/eis/vocab/qpro#exceptionDescription" );
    
    /** <p>Enables the description of extra exception properties. This is an abstract 
     *  property.</p>
     */
    public static final Property extraExceptionProperty = M_MODEL.createProperty( "http://purl.org/eis/vocab/qpro#extraExceptionProperty" );
    
    /** <p>Identifies problem instances in the report</p> */
    public static final Property hasProblem = M_MODEL.createProperty( "http://purl.org/eis/vocab/qpro#hasProblem" );
    
    /** <p>This property enables the reification of quads by creating a new property 
     *  to define the context of a triple statement.</p>
     */
    public static final Property inGraph = M_MODEL.createProperty( "http://purl.org/eis/vocab/qpro#inGraph" );
    
    /** <p>Represent the URI of the metric which instantiated this problem</p> */
    public static final Property isDescribedBy = M_MODEL.createProperty( "http://purl.org/eis/vocab/qpro#isDescribedBy" );
    
    /** <p>Defines the problem report structure for the metric. This is useful for third-parties 
     *  using the Luzzu Quality Problem Report instances to know how to interpret 
     *  the results. The problem structure can be one of the following three: QuadContainer, 
     *  ResourceContainer, or ModelContainer</p>
     */
    public static final Property problemStructure = M_MODEL.createProperty( "http://purl.org/eis/vocab/qpro#problemStructure" );
    
    /** <p>Represent the actual problematic instance from the dataset. This could be 
     *  a list of resources (rdf:Seq), a list of reified statements, or a model representing 
     *  a list of statements.</p>
     */
    public static final Property problematicThing = M_MODEL.createProperty( "http://purl.org/eis/vocab/qpro#problematicThing" );
    
    /** <p>Represents an Exception that causes the dataset to have Quality Problems. 
     *  This is an abstract class</p>
     */
    public static final Resource Exception = M_MODEL.createResource( "http://purl.org/eis/vocab/qpro#Exception" );
    
    /** <p>Represents the structure of the quality problem in the report. This is an 
     *  abstract class</p>
     */
    public static final Resource ProblemReportStructure = M_MODEL.createResource( "http://purl.org/eis/vocab/qpro#ProblemReportStructure" );
    
    /** <p>Represents a quality problem detected during the assessment of quality metrics 
     *  on triples.</p>
     */
    public static final Resource QualityProblem = M_MODEL.createResource( "http://purl.org/eis/vocab/qpro#QualityProblem" );
    
    /** <p>Represents a report on the problems detected during the assessment of quality 
     *  on a dataset.</p>
     */
    public static final Resource QualityReport = M_MODEL.createResource( "http://purl.org/eis/vocab/qpro#QualityReport" );
    
    public static final Resource __ = M_MODEL.createResource( "http://purl.org/eis/vocab/qpro#" );
    
    public static final Resource ModelContainer = M_MODEL.createResource( "http://purl.org/eis/vocab/qpro#ModelContainer" );
    
    public static final Resource QuadContainer = M_MODEL.createResource( "http://purl.org/eis/vocab/qpro#QuadContainer" );
    
    public static final Resource ResourceContainer = M_MODEL.createResource( "http://purl.org/eis/vocab/qpro#ResourceContainer" );
    
}
