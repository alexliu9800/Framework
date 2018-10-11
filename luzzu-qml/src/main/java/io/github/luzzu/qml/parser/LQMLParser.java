/* Generated By:JavaCC: Do not edit this line. LQMLParser.java */
package io.github.luzzu.qml.parser;

import java.io.Reader ;
import java.io.StringReader ;

import java.util.ArrayList;
import java.util.List;
import java.util.Map ;
import java.util.HashMap ;

import io.github.luzzu.qml.datatypes.conditions.CustomCondition;
import io.github.luzzu.qml.datatypes.conditions.DefinedCondition;
import io.github.luzzu.qml.datatypes.conditions.Condition;
import io.github.luzzu.qml.datatypes.conditions.ConditionType;
import io.github.luzzu.qml.datatypes.Metric;
import io.github.luzzu.qml.datatypes.actions.ActionType;
import io.github.luzzu.qml.datatypes.actions.Action;
import io.github.luzzu.qml.datatypes.functions.Function;
import io.github.luzzu.qml.datatypes.functions.FunctionType;
import io.github.luzzu.qml.datatypes.functions.FunctionParam;
import io.github.luzzu.qml.datatypes.functions.FunctionParamType;
import io.github.luzzu.qml.datatypes.Rule;
import io.github.luzzu.qml.datatypes.RuleSet;
public class LQMLParser implements LQMLParserConstants {
  static Map<String,String> compilerMap;
  static String def, label, desc, metricUri, agentUri, estimate;
  //static Rule rule;  static Action action;
  static RuleSet rules;
  static Function finalVal;
  Map<String,CustomCondition> customConditions;

  public void setCustomConditions(Map<String, CustomCondition> map)
  {
    customConditions = map;
  }

// BNF Definition  final public Map<String,String> parse() throws ParseException {
 compilerMap = new HashMap<String,String>();
    extractHeader();
    def = extractDefinitionName();
    AgentEstimatePerm();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case METRICURI:
      metricUri = extractMetricURI();
      AgentEstimatePerm();
      LabelDescRulesPerm();
      break;
    case LABEL:
      label = extractLabel();
      AgentEstimatePerm();
      MetricUriDescRulesPerm();
      break;
    case DESC:
      desc = extractDescription();
      AgentEstimatePerm();
      MetricUriLabelRulesPerm();
      break;
    case RULE:
      rules = extractRules();
      AgentEstimatePerm();
      MetricUriLabelDescPerm();
      break;
    default:
      jj_la1[0] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    AgentEstimatePerm();
    finalVal = extractFinally();
    jj_consume_token(PERIOD);
    jj_consume_token(0);
        compilerMap.put("[%%classname%%]",def);
        compilerMap.put("[%%metricuri%%]",metricUri);
        compilerMap.put("[%%label%%]",label);
        compilerMap.put("[%%description%%]",desc);

        Metric metric = new Metric(rules, finalVal);
        compilerMap.put("[%%computefunction%%]",metric.getComputeFunction());
        compilerMap.put("[%%imports%%]",metric.getImports());
        compilerMap.put("[%%variables%%]",metric.getVariables());
        compilerMap.put("[%%metricvaluefuntion%%]",metric.getFinalMetricValueFunction());
        {if (true) return compilerMap;}
    throw new Error("Missing return statement in function");
  }

  final public void extractHeader() throws ParseException {
    compilerMap.put("[%%author%%]",extractAuthor());
    compilerMap.put("[%%packagename%%]",extractPackage());
  }

  final public String extractAuthor() throws ParseException {
  Token t;
  String author = "";
    jj_consume_token(HEADER_INDICATOR);
    jj_consume_token(AUTHOR);
    jj_consume_token(COLON);
    t = jj_consume_token(QUOTED_STR);
  author = t.image.replace("\u005c"","") ;
  {if (true) return author;}
    throw new Error("Missing return statement in function");
  }

  final public String extractPackage() throws ParseException {
  Token t;
  String pkg = "";
    jj_consume_token(HEADER_INDICATOR);
    jj_consume_token(PACKAGE);
    jj_consume_token(COLON);
    t = jj_consume_token(QUOTED_STR);
          pkg = t.image.replace("\u005c"","")  ;
          {if (true) return pkg;}
    throw new Error("Missing return statement in function");
  }

  final public String extractDefinitionName() throws ParseException {
  Token t;
    jj_consume_token(DEF);
    jj_consume_token(LBRACE);
    t = jj_consume_token(STRICT_STR);
         String s = t.image.replace("\u005c"","") ;
    jj_consume_token(RBRACE);
    jj_consume_token(COLON);
         {if (true) return s;}
    throw new Error("Missing return statement in function");
  }

  final public String extractLabel() throws ParseException {
  Token t;
    jj_consume_token(LABEL);
    jj_consume_token(LBRACE);
    t = jj_consume_token(QUOTED_STR);
          String s = t.image.replace("\u005c"","") ;
    jj_consume_token(RBRACE);
    jj_consume_token(SEMI_COLON);
         {if (true) return s;}
    throw new Error("Missing return statement in function");
  }

  final public String extractDescription() throws ParseException {
  Token t;
    jj_consume_token(DESC);
    jj_consume_token(LBRACE);
    t = jj_consume_token(QUOTED_STR);
          String s = t.image;
    jj_consume_token(RBRACE);
    jj_consume_token(SEMI_COLON);
         {if (true) return s;}
    throw new Error("Missing return statement in function");
  }

  final public RuleSet extractRules() throws ParseException {
  RuleSet ruleSet = new RuleSet();
  List<Rule> rules = new ArrayList<Rule>();
    jj_consume_token(RULE);
    jj_consume_token(LBRACE);
    extractRule(rules);
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VARIABLE:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_1;
      }
      extractRule(rules);
    }
    jj_consume_token(RBRACE);
    jj_consume_token(SEMI_COLON);
    ruleSet.setRuleSet(rules);
    {if (true) return ruleSet;}
    throw new Error("Missing return statement in function");
  }

  final public void extractRule(List<Rule> rules) throws ParseException {
  Rule r = new Rule();
  Token t;
    jj_consume_token(VARIABLE);
    jj_consume_token(PERIOD);
    t = jj_consume_token(STRICT_STR);
    jj_consume_token(ASSIGNMENT_OPERATOR);
    r.setResultVar(t.image);
    jj_consume_token(MATCH);
    jj_consume_token(LBRACE);
    extractCondition(r);
    jj_consume_token(RBRACE);
    jj_consume_token(ASSOCIATION_OPERATOR);
    action = extractAction();
    jj_consume_token(SEMI_COLON);
    r.setAction(action);
    rules.add(r);
  }

  final public void extractAutomicCondition(Rule r) throws ParseException {
  Condition c;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case TYPEOF:
      jj_consume_token(TYPEOF);
          c = extractTypeOf();
          r.addCondition(c);
      break;
    case ISURI:
      jj_consume_token(ISURI);
      c = extractIsUri();
      r.addCondition(c);
      break;
    case ISBLANK:
      jj_consume_token(ISBLANK);
          c = extractIsBlank();
          r.addCondition(c);
      break;
    case ISLITERAL:
      jj_consume_token(ISLITERAL);
      c = extractIsLiteral();
      r.addCondition(c);
      break;
    case CUSTOM:
      jj_consume_token(CUSTOM);
      jj_consume_token(PERIOD);
          c = extractCustom();
          r.addCondition(c);
      break;
    default:
      jj_la1[2] = jj_gen;
        c = extractNonDefinedCondition();
        r.addCondition(c);
    }
  }

  final public void extractCompoundCondition(Rule r) throws ParseException {
  Token t;
    jj_consume_token(LPAREN);
    r.addOperator("(");
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LOGICAL_NOT:
      t = jj_consume_token(LOGICAL_NOT);
        r.addOperator(t.image);
      extractAutomicCondition(r);
      break;
    default:
      jj_la1[3] = jj_gen;
      extractAutomicCondition(r);
    }
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LOGICAL_BINARY_OP:
        ;
        break;
      default:
        jj_la1[4] = jj_gen;
        break label_2;
      }
      t = jj_consume_token(LOGICAL_BINARY_OP);
      r.addOperator(t.image);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LOGICAL_NOT:
        t = jj_consume_token(LOGICAL_NOT);
          r.addOperator(t.image);
        extractAutomicCondition(r);
        break;
      default:
        jj_la1[5] = jj_gen;
        extractAutomicCondition(r);
      }
    }
    jj_consume_token(RPAREN);
    r.addOperator(")");
  }

  final public void extractCondition(Rule r) throws ParseException {
  Token t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LOGICAL_NOT:
      t = jj_consume_token(LOGICAL_NOT);
        r.addOperator(t.image);
      extractAutomicCondition(r);
      break;
    default:
      jj_la1[6] = jj_gen;
      extractAutomicCondition(r);
    }
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LOGICAL_BINARY_OP:
        ;
        break;
      default:
        jj_la1[7] = jj_gen;
        break label_3;
      }
      t = jj_consume_token(LOGICAL_BINARY_OP);
        r.addOperator(t.image);
      extractCondition(r);
    }
  }

  final public void extractParams(ArrayList<String> params) throws ParseException {
  Token t;
    jj_consume_token(LPAREN);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case SUBJECT_VAR:
      t = jj_consume_token(SUBJECT_VAR);
                            params.add(t.image);
      break;
    case PREDICATE_VAR:
      t = jj_consume_token(PREDICATE_VAR);
                                params.add(t.image);
      break;
    case OBJECT_VAR:
      t = jj_consume_token(OBJECT_VAR);
                             params.add(t.image);
      break;
    default:
      jj_la1[8] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[9] = jj_gen;
        break label_4;
      }
      jj_consume_token(COMMA);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SUBJECT_VAR:
        t = jj_consume_token(SUBJECT_VAR);
                             params.add(t.image);
        break;
      case PREDICATE_VAR:
        t = jj_consume_token(PREDICATE_VAR);
                                params.add(t.image);
        break;
      case OBJECT_VAR:
        t = jj_consume_token(OBJECT_VAR);
                             params.add(t.image);
        break;
      default:
        jj_la1[10] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    jj_consume_token(RPAREN);
  }

  final public Condition extractCustom() throws ParseException {
  Token t;
  ArrayList<String> params = new ArrayList<String>();
    t = jj_consume_token(STRICT_STR);
    //c.setClassName(t.image);
        Condition c = customConditions.get(t.image);
    extractParams(params);
    c.setParams(params);
    c.setConditionType(ConditionType.CUSTOM);
    {if (true) return c;}
    throw new Error("Missing return statement in function");
  }

  final public Condition extractIsLiteral() throws ParseException {
  Token t;
  Condition c = new Condition();
  ArrayList<String> params = new ArrayList<String>();
    extractParams(params);
                c.setConditionType(ConditionType.ISLITERAL);
                c.setParams(params);
                {if (true) return c;}
    throw new Error("Missing return statement in function");
  }

  final public Condition extractIsBlank() throws ParseException {
  Token t;
  Condition c = new Condition();
  ArrayList<String> params = new ArrayList<String>();
    extractParams(params);
                c.setConditionType(ConditionType.ISBLANK);
                c.setParams(params);
                {if (true) return c;}
    throw new Error("Missing return statement in function");
  }

  final public Condition extractIsUri() throws ParseException {
  Token t;
  Condition c = new Condition();
  ArrayList<String> params = new ArrayList<String>();
    extractParams(params);
                c.setConditionType(ConditionType.ISURI);
                c.setParams(params);
                {if (true) return c;}
    throw new Error("Missing return statement in function");
  }

  final public Condition extractTypeOf() throws ParseException {
  Token t;
  Condition c = new Condition();
    jj_consume_token(LPAREN);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case SUBJECT_VAR:
      t = jj_consume_token(SUBJECT_VAR);
                              c.addParam(t.image);
      break;
    case OBJECT_VAR:
      t = jj_consume_token(OBJECT_VAR);
                             c.addParam(t.image);
      break;
    case PREDICATE_VAR:
      t = jj_consume_token(PREDICATE_VAR);
                               c.addParam(t.image);
      break;
    default:
      jj_la1[11] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(RPAREN);
    t = jj_consume_token(BOOLEAN_OPERATOR);
          c.addParam(t.image);
    t = jj_consume_token(IRIref);
                c.setConditionType(ConditionType.TYPEOF);
                c.addParam(t.image);
                {if (true) return c;}
    throw new Error("Missing return statement in function");
  }

  final public Condition extractNonDefinedCondition() throws ParseException {
  Token t, t2, t3;
  DefinedCondition c = new DefinedCondition();
  c.setConditionType(ConditionType.NORMAL);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case SUBJECT_VAR:
      t = jj_consume_token(SUBJECT_VAR);
            c.setLhs("?s");
      t2 = jj_consume_token(BOOLEAN_OPERATOR);
      t3 = jj_consume_token(IRIref);
      break;
    case PREDICATE_VAR:
      t = jj_consume_token(PREDICATE_VAR);
          c.setLhs("?p");
      t2 = jj_consume_token(BOOLEAN_OPERATOR);
      t3 = jj_consume_token(IRIref);
                  c.setIsIRI(true);
      break;
    case OBJECT_VAR:
      t = jj_consume_token(OBJECT_VAR);
                    c.setLhs("?o");
      t2 = jj_consume_token(BOOLEAN_OPERATOR);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case IRIref:
        t3 = jj_consume_token(IRIref);
                         c.setIsIRI(true);
        break;
      case QUOTED_STR:
        t3 = jj_consume_token(QUOTED_STR);
        break;
      default:
        jj_la1[12] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[13] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
                c.setBooleanOperator(t2.image);
                c.setRhs(t3.image);
                {if (true) return c;}
    throw new Error("Missing return statement in function");
  }

  final public void extractFunctionParams(List< FunctionParam> result) throws ParseException {
    jj_consume_token(LPAREN);
    extractFunctionParam(result);
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[14] = jj_gen;
        break label_5;
      }
      jj_consume_token(COMMA);
      extractFunctionParam(result);
    }
    jj_consume_token(RPAREN);
  }

  final public void extractFunctionParam(List<FunctionParam> funParams) throws ParseException {
  Token t;
  FunctionParam param = new FunctionParam();
  List<FunctionParam> subParams = new ArrayList< FunctionParam>();
  Function fun = new Function();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ADD:
      jj_consume_token(ADD);
      extractFunctionParams(subParams);
                  param.setParamType(FunctionParamType.FUNCTION);

                  fun.setFunctionType(FunctionType.ADD);
                  fun.setParams(subParams);
                  param.setFunction(fun);
      break;
    case RATIO:
      jj_consume_token(RATIO);
      extractFunctionParams(subParams);
              param.setParamType(FunctionParamType.FUNCTION);

                  fun.setFunctionType(FunctionType.RATIO);
                  fun.setParams(subParams);
                  param.setFunction(fun);
      break;
    case NORMALISE:
      jj_consume_token(NORMALISE);
      extractFunctionParams(subParams);
              param.setParamType(FunctionParamType.FUNCTION);

                  fun.setFunctionType(FunctionType.NORMALISE);
                  fun.setParams(subParams);
                  param.setFunction(fun);
      break;
    case VARIABLE:
      jj_consume_token(VARIABLE);
      jj_consume_token(PERIOD);
      t = jj_consume_token(STRICT_STR);
              param.setParamType(FunctionParamType.VAR);
              param.setVarName(t.image);
      break;
    case TOTALTRIPLES:
      jj_consume_token(TOTALTRIPLES);
              param.setParamType(FunctionParamType.TOTALTRIPLES);
              param.setVarName("totalTriplesAssessed");
      break;
    default:
      jj_la1[15] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
          funParams.add(param);
  }

  final public Function extractFinally() throws ParseException {
  Token t;
  Function fun = new Function();
  List<FunctionParam > funParams = new ArrayList<FunctionParam>();
    jj_consume_token(FINALLY);
    jj_consume_token(LBRACE);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ADD:
      jj_consume_token(ADD);
        fun.setFunctionType(FunctionType.ADD);
      break;
    case RATIO:
      jj_consume_token(RATIO);
        fun.setFunctionType(FunctionType.RATIO);
      break;
    case NORMALISE:
      jj_consume_token(NORMALISE);
        fun.setFunctionType(FunctionType.NORMALISE);
      break;
    default:
      jj_la1[16] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    extractFunctionParams(funParams);
    fun.setParams(funParams);
    jj_consume_token(RBRACE);
    {if (true) return fun;}
    throw new Error("Missing return statement in function");
  }

  final public Action extractAction() throws ParseException {
  Token t;
  Action action = new Action();
  ArrayList<String> params = new ArrayList<String>();
    jj_consume_token(ACTION);
    jj_consume_token(LBRACE);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case COUNT:
      jj_consume_token(COUNT);
      extractParams(params);
        action.setActionType(ActionType.COUNT);
        action.setParams(params);
      break;
    case COUNTUNIQUE:
      jj_consume_token(COUNTUNIQUE);
      extractParams(params);
        action.setActionType(ActionType.COUNTUNIQUE);
        action.setParams(params);
      break;
    case CUSTOM:
      jj_consume_token(CUSTOM);
      jj_consume_token(PERIOD);
      t = jj_consume_token(STRICT_STR);
        action.setClassName(t.image);
      extractParams(params);
        action.setActionType(ActionType.CUSTOM);
      break;
    default:
      jj_la1[17] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(RBRACE);
    {if (true) return action;}
    throw new Error("Missing return statement in function");
  }

  final public String extractEstimate() throws ParseException {
        Token t;
    jj_consume_token(ESTIMATE);
    jj_consume_token(LBRACE);
    t = jj_consume_token(BOOLEAN);
                String s = t.image;
    jj_consume_token(RBRACE);
    jj_consume_token(SEMI_COLON);
                {if (true) return s;}
    throw new Error("Missing return statement in function");
  }

  final public String extractAgentURI() throws ParseException {
        Token t;
    jj_consume_token(AGENT);
    jj_consume_token(LBRACE);
    t = jj_consume_token(IRIref);
                String s = t.image.replace("<","").replace(">","");
    jj_consume_token(RBRACE);
    jj_consume_token(SEMI_COLON);
                {if (true) return s;}
    throw new Error("Missing return statement in function");
  }

  final public String extractMetricURI() throws ParseException {
        Token t;
    jj_consume_token(METRICURI);
    jj_consume_token(LBRACE);
    t = jj_consume_token(IRIref);
   String s = t.image.replace("<","").replace(">","") ;
    jj_consume_token(RBRACE);
    jj_consume_token(SEMI_COLON);
   {if (true) return s;}
    throw new Error("Missing return statement in function");
  }

  final public void LabelDescRulesPerm() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LABEL:
      label = extractLabel();
      AgentEstimatePerm();
      DescRulesPerm();
      break;
    case DESC:
      desc = extractDescription();
      AgentEstimatePerm();
      LabelRulesPerm();
      break;
    case RULE:
      rules = extractRules();
      AgentEstimatePerm();
      LabelDescPerm();
      break;
    default:
      jj_la1[18] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void MetricUriDescRulesPerm() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case METRICURI:
      metricUri = extractMetricURI();
      AgentEstimatePerm();
      DescRulesPerm();
      break;
    case DESC:
      desc = extractDescription();
      AgentEstimatePerm();
      MetricUriRulesPerm();
      break;
    case RULE:
      rules = extractRules();
      AgentEstimatePerm();
      MetricUriDescPerm();
      break;
    default:
      jj_la1[19] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void MetricUriLabelRulesPerm() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case METRICURI:
      metricUri = extractMetricURI();
      AgentEstimatePerm();
      LabelRulesPerm();
      break;
    case LABEL:
      label = extractLabel();
      AgentEstimatePerm();
      MetricUriRulesPerm();
      break;
    case RULE:
      rules = extractRules();
      AgentEstimatePerm();
      MetricUriLabelPerm();
      break;
    default:
      jj_la1[20] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void MetricUriLabelDescPerm() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case METRICURI:
      metricUri = extractMetricURI();
      AgentEstimatePerm();
      LabelDescPerm();
      break;
    case LABEL:
      label = extractLabel();
      AgentEstimatePerm();
      MetricUriDescPerm();
      break;
    case DESC:
      desc = extractDescription();
      AgentEstimatePerm();
      MetricUriLabelPerm();
      break;
    default:
      jj_la1[21] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void MetricUriDescPerm() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case METRICURI:
      metricUri = extractMetricURI();
      AgentEstimatePerm();
      break;
    case DESC:
      desc = extractDescription();
      AgentEstimatePerm();
      break;
    default:
      jj_la1[22] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void MetricUriRulesPerm() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case METRICURI:
      metricUri = extractMetricURI();
      AgentEstimatePerm();
      break;
    case RULE:
      rules = extractRules();
      AgentEstimatePerm();
      break;
    default:
      jj_la1[23] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void MetricUriLabelPerm() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case METRICURI:
      metricUri = extractMetricURI();
      AgentEstimatePerm();
      break;
    case LABEL:
      label = extractLabel();
      AgentEstimatePerm();
      break;
    default:
      jj_la1[24] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void DescRulesPerm() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case DESC:
      desc = extractDescription();
      AgentEstimatePerm();
      rules = extractRules();
      break;
    case RULE:
      rules = extractRules();
      AgentEstimatePerm();
      desc = extractDescription();
      break;
    default:
      jj_la1[25] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void LabelRulesPerm() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LABEL:
      label = extractLabel();
      AgentEstimatePerm();
      break;
    case RULE:
      rules = extractRules();
      AgentEstimatePerm();
      break;
    default:
      jj_la1[26] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void LabelDescPerm() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LABEL:
      label = extractLabel();
      AgentEstimatePerm();
      break;
    case DESC:
      desc = extractDescription();
      AgentEstimatePerm();
      break;
    default:
      jj_la1[27] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void AgentEstimatePerm() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case AGENT:
      agentUri = extractAgentURI();
      break;
    default:
      jj_la1[28] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ESTIMATE:
      estimate = extractEstimate();
      break;
    default:
      jj_la1[29] = jj_gen;
      ;
    }
  }

  /** Generated Token Manager. */
  public LQMLParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[32];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x40000980,0x400000,0x3e0000,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x3c400000,0x1c000000,0x3200000,0x40000180,0x40000900,0x40000880,0x980,0x900,0x40000800,0x880,0x40000100,0x40000080,0x180,0x2000,0x4000,0x4000,0x2000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x0,0x400000,0x200000,0x400000,0x400000,0x200000,0x1c000000,0x200,0x1c000000,0x1c000000,0x20000010,0x1c000000,0x200,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,};
   }

  /** Constructor with InputStream. */
  public LQMLParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public LQMLParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new LQMLParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 32; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 32; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public LQMLParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new LQMLParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 32; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 32; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public LQMLParser(LQMLParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 32; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(LQMLParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 32; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[62];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 32; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 62; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
