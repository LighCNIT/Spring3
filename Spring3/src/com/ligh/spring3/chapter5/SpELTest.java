package com.ligh.spring3.chapter5;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * SPEL锟斤拷锟斤拷锟斤拷式值一锟斤拷锟斤拷牟锟斤拷锟斤拷锟斤拷械锟斤拷锟斤拷锟斤拷锟窖★拷锟�
 * 锟斤拷锟饺癸拷锟斤拷一锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷ExpressionParser锟斤拷默锟斤拷实锟斤拷锟斤拷 SpelExpressionParser
 *  锟斤拷谓锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷址锟斤拷锟斤拷锟斤拷式
 * 锟节此癸拷锟斤拷锟斤拷锟斤拷锟侥ｏ拷EvaluationContext锟斤拷默锟斤拷实锟斤拷 StandardEvaluationContext锟斤拷锟斤拷选锟斤拷
 * 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟侥得碉拷锟斤拷锟绞斤拷锟斤拷锟斤拷锟街�
 * @author Administrator
 *
 */
public class SpELTest {
		
	@Test
	public void helloWorld(){
		ExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("('Hello' + ' World').concat(#end)");
		EvaluationContext context = new StandardEvaluationContext();
		//注锟斤拷锟皆讹拷锟斤拷锟斤拷锟�
		context.setVariable("end", "!");
		Assert.assertEquals("Hello World!", expression.getValue(context));
	}
	
	//使锟斤拷ParserContext锟接匡拷 
	@Test
	public void testParserContext(){
		ExpressionParser parser = new SpelExpressionParser();
		ParserContext parserContext = new ParserContext() {
			
			@Override
			public boolean isTemplate() {
				return true;
			}
			
			@Override
			public String getExpressionSuffix() {
				return "}";
			}
			
			@Override
			public String getExpressionPrefix() {
				return "#{";
			}
		};
		String template = "#{'Hello '}#{'World!'}";
		Expression expression = parser.parseExpression(template,parserContext);
		Assert.assertEquals("Hello World!", expression.getValue(template));
	}
	
	//锟斤拷锟皆伙拷锟斤拷锟斤拷锟斤拷
	@Test
    public void testBasicExpression() {
        ExpressionParser parser = new SpelExpressionParser();
        //1.锟街凤拷锟斤拷
        String str1 = parser.parseExpression("'Hello World!'").getValue(String.class);
        String str2 = parser.parseExpression("\"Hello World!\"").getValue(String.class);
        Assert.assertEquals(str1, str2);
        
        int int1 = parser.parseExpression("1").getValue(Integer.class);//int锟斤拷锟斤拷
        Assert.assertEquals(1, int1);

        long long1 = parser.parseExpression("-1L").getValue(long.class);//long锟斤拷锟斤拷
        Assert.assertEquals(-1L, long1);

        float float1 = parser.parseExpression("1.1f").getValue(float.class);//float锟斤拷锟斤拷
        System.out.println(float1);
        Assert.assertNotEquals(1.1, float1);  //锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷示前锟斤拷锟斤拷锟斤拷锟斤拷锟侥凤拷围
        
        double double1 = parser.parseExpression("1.1E+2").getValue(double.class);//double锟斤拷锟斤拷
        Assert.assertEquals(1.1E+2, double1,0.000001);
        
        int hex1 = parser.parseExpression("0xa").getValue(Integer.class);//16锟斤拷锟斤拷int锟斤拷锟斤拷
        Assert.assertEquals(0xa, hex1);

        long hex2 = parser.parseExpression("0xaL").getValue(long.class);//16锟斤拷锟斤拷long锟斤拷锟斤拷
        Assert.assertEquals(0xaL, hex2);
        
        boolean true1 = parser.parseExpression("true").getValue(boolean.class);//锟斤拷锟斤拷锟斤拷锟斤拷
        Assert.assertEquals(true, true1);

        boolean false1 = parser.parseExpression("false").getValue(boolean.class);//锟斤拷锟斤拷锟斤拷锟斤拷
        Assert.assertEquals(false, false1);
        
        Object null1 = parser.parseExpression("null").getValue(Object.class);//null锟斤拷锟斤拷
        Assert.assertEquals(null, null1);
        
    }
    
	//锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
    @Test
    public void testArithmeticExpression() {
        ExpressionParser parser = new SpelExpressionParser();
        int result1 = parser.parseExpression("1+2-3*4/2").getValue(Integer.class);
        Assert.assertEquals(-3, result1);

        int result2 = parser.parseExpression("4%3").getValue(Integer.class);
        Assert.assertEquals(1, result2);


        int result3 = parser.parseExpression("2^3").getValue(Integer.class);
        Assert.assertEquals(8, result3);

        int result4 = parser.parseExpression("6 DIV 2").getValue(Integer.class);
        Assert.assertEquals(3, result4);

        int result5 = parser.parseExpression("4 MOD 3").getValue(Integer.class);
        Assert.assertEquals(1, result5);
    }
    
    //锟斤拷锟斤拷锟斤拷锟斤拷
    @Test
    public void testRelationalExpression() {
        ExpressionParser parser = new SpelExpressionParser();
        boolean result1 = parser.parseExpression("1 gt 2").getValue(boolean.class);
        Assert.assertEquals(false, result1);
        
        boolean result2 = parser.parseExpression("1 between {1, 2}").getValue(boolean.class);
        Assert.assertEquals(true, result2);
    }

    //锟竭硷拷锟斤拷锟斤拷
    @Test
    public void testLogicalExpression() {
        ExpressionParser parser = new SpelExpressionParser();
        String expression1 = "2>1 and (!true or !false)";
        boolean result1 = parser.parseExpression(expression1).getValue(boolean.class);
        Assert.assertEquals(true, result1);

        String expression2 = "2>1 and (NOT true or NOT false)";
        boolean result2 = parser.parseExpression(expression2).getValue(boolean.class);
        Assert.assertEquals(true, result2);
    }

    @Test
    public void testStringConcatExpression() {
        ExpressionParser parser = new SpelExpressionParser();
        String expression1 = "'Hello ' + 'World!'";
        String result1 = parser.parseExpression(expression1).getValue(String.class);
        Assert.assertEquals("Hello World!", result1);

        String expression2 = "'Hello World!'[0]";
        String result2 = parser.parseExpression(expression2).getValue(String.class);
        Assert.assertEquals("H", result2);
        
    }
    
    
    @Test
    public void testTernaryExpression() {
        ExpressionParser parser = new SpelExpressionParser();
        String expression1 = "2>1?true:false";
        boolean result1 = parser.parseExpression(expression1).getValue(boolean.class);
        Assert.assertEquals(true, result1);

        String expression2 = "null?:false";
        boolean result2 = parser.parseExpression(expression2).getValue(boolean.class);
        Assert.assertEquals(false, result2);

        String expression3 = "true?:false";
        boolean result3 = parser.parseExpression(expression3).getValue(boolean.class);
        Assert.assertEquals(true, result3);
        
    }

    //锟斤拷锟斤拷
    @Test
    public void testRegexExpression() {
        ExpressionParser parser = new SpelExpressionParser();
        String expression1 = "'123' matches '\\d{3}'";
        boolean result1 = parser.parseExpression(expression1).getValue(boolean.class);
        Assert.assertEquals(true, result1);
        
    }

    @SuppressWarnings("unchecked")
	@Test
    public void testClassTypeExpression() {
        ExpressionParser parser = new SpelExpressionParser();
        //java.lang锟斤拷锟斤拷锟斤拷锟�
        Class<String> result1 = parser.parseExpression("T(String)").getValue(Class.class);
        Assert.assertEquals(String.class, result1);

        //锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�
        String expression2 = "T(com.ligh.spring3.chapter5.SpELTest)"; 
        Class<String> result2 = parser.parseExpression(expression2).getValue(Class.class);
        Assert.assertEquals(SpELTest.class, result2);
        
        //锟洁静态锟街段凤拷锟斤拷
        int result3 = parser.parseExpression("T(Integer).MAX_VALUE").getValue(int.class);
        Assert.assertEquals(Integer.MAX_VALUE, result3);

        //锟洁静态锟斤拷锟斤拷锟斤拷锟斤拷
        int result4 = parser.parseExpression("T(Integer).parseInt('1')").getValue(int.class);
        Assert.assertEquals(1, result4);
    }

    @Test
    public void testConstructorExpression() {
        ExpressionParser parser = new SpelExpressionParser();
        String result1 = parser.parseExpression("new String('haha')").getValue(String.class);
        Assert.assertEquals("haha", result1);

        Date result2 = parser.parseExpression("new java.util.Date()").getValue(Date.class);
        Assert.assertNotNull(result2);
    }
    

    @Test
    public void testInstanceofExpression() {
        ExpressionParser parser = new SpelExpressionParser();
        boolean result1 = parser.parseExpression("'haha' instanceof T(String)").getValue(boolean.class);
        Assert.assertEquals(true, result1);
    }

    /**
     * 锟斤拷锟斤拷锟斤拷锟藉及锟斤拷锟矫ｏ拷锟斤拷锟斤拷锟斤拷锟斤拷通锟斤拷EvaluationContext锟接口碉拷setVariable(variableName, value)锟斤拷锟斤拷锟斤拷锟藉；
     * 锟节憋拷锟绞斤拷锟绞癸拷谩锟�#variableName锟斤拷锟斤拷锟矫ｏ拷
     * 锟斤拷锟斤拷锟斤拷锟斤拷锟皆讹拷锟斤拷锟斤拷锟斤拷锟絊pE锟斤拷锟斤拷锟斤拷锟斤拷锟矫革拷锟斤拷锟襟及碉拷前锟斤拷锟斤拷锟侥讹拷锟斤拷使锟矫★拷#root锟斤拷锟斤拷锟矫革拷锟斤拷锟斤拷使锟矫★拷#this锟斤拷锟斤拷锟矫碉拷前锟斤拷锟斤拷锟侥讹拷锟斤拷
     */
    @Test
    public void testVariableExpression() {
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("variable", "haha");
        String result1 = parser.parseExpression("#variable").getValue(context, String.class);
        Assert.assertEquals("haha", result1);

        context = new StandardEvaluationContext("haha");
        String result2 = parser.parseExpression("#root").getValue(context, String.class);
        Assert.assertEquals("haha", result2);

        String result3 = parser.parseExpression("#this").getValue(context, String.class);
        Assert.assertEquals("haha", result3);
    }
    
    
    //锟皆讹拷锟藉函锟斤拷
    @Test
    public void testFunctionExpression() throws SecurityException, NoSuchMethodException {
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        Method parseInt = Integer.class.getDeclaredMethod("parseInt", String.class);
        context.registerFunction("parseInt", parseInt);
        context.setVariable("parseInt2", parseInt);
        String expression1 = "#parseInt('3') == #parseInt2('3')";
        boolean result1 = parser.parseExpression(expression1).getValue(context, boolean.class);
        Assert.assertEquals(true, result1);
        
    }
    
    @Test
    public void testAssignExpression() {
        ExpressionParser parser = new SpelExpressionParser();
        //1.锟斤拷root锟斤拷锟斤拷值
        EvaluationContext context = new StandardEvaluationContext("aaaa");
        String result1 = parser.parseExpression("#root='aaaaa'").getValue(context, String.class);
        Assert.assertEquals("aaaaa", result1);
        String result2 = parser.parseExpression("#this='aaaa'").getValue(context, String.class);
        Assert.assertEquals("aaaa", result2);

        //2.锟斤拷锟皆讹拷锟斤拷锟斤拷锟斤拷锟街�
        context.setVariable("#variable", "variable");
        String result3 = parser.parseExpression("#variable=#root").getValue(context, String.class);
        Assert.assertEquals("aaaa", result3);
    }

    @SuppressWarnings("deprecation")
	@Test
    public void testPropertyExpression() {
        ExpressionParser parser = new SpelExpressionParser();
        //1.锟斤拷锟斤拷root锟斤拷锟斤拷锟斤拷锟斤拷
        Date date = new Date();
        StandardEvaluationContext context = new StandardEvaluationContext(date);
        int result1 = parser.parseExpression("Year").getValue(context, int.class);
        Assert.assertEquals(date.getYear(), result1);
        int result2 = parser.parseExpression("year").getValue(context, int.class);
        Assert.assertEquals(date.getYear(), result2);
        
        //2.锟斤拷全锟斤拷锟斤拷
        //SpEL锟斤拷锟斤拷锟斤拷Groovy锟侥帮拷全锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟剿达拷锟斤拷锟斤拷锟斤拷为null锟斤拷
        //锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟绞憋拷隙锟斤拷壮锟斤拷锟街革拷锟斤拷斐ｏ拷锟斤拷锟斤拷锟斤拷谩锟�?.锟斤拷锟斤拷全锟斤拷锟绞碉拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷卓锟街革拷锟斤拷斐ｏ拷锟斤拷锟斤拷羌虻サ姆锟斤拷锟絥ull
        context.setRootObject(null);
        Object result3 = parser.parseExpression("#root?.year").getValue(context, Object.class);
        Assert.assertEquals(null, result3);
        
        //3.锟斤拷root锟斤拷锟斤拷锟斤拷锟皆革拷值
        context.setRootObject(date);
        int result4 = parser.parseExpression("Year = 4").getValue(context, int.class);
        Assert.assertEquals(4, result4);
        parser.parseExpression("Year").setValue(context, 5);
        int result5 = parser.parseExpression("Year").getValue(context, int.class);
        Assert.assertEquals(5, result5);
    }
    

    
    @SuppressWarnings("deprecation")
	@Test
    public void testMethodExpression() {
        ExpressionParser parser = new SpelExpressionParser();
        String result1 = parser.parseExpression("'haha'.substring(2,4)").getValue(String.class);
        Assert.assertEquals("ha", result1);
        Date date = new Date();
        StandardEvaluationContext context = new StandardEvaluationContext(date);
        int result2 = parser.parseExpression("getYear()").getValue(context, int.class);
        Assert.assertEquals(date.getYear(), result2);
    }

    //Bean 锟斤拷锟斤拷
    /**
     * 锟斤拷示锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟饺筹拷始锟斤拷锟斤拷一锟斤拷IoC锟斤拷锟斤拷锟斤拷ClassPathXmlApplicationContext 
     * 实锟斤拷默锟较伙拷选锟絊ystem.getProperties()锟斤拷注锟斤拷为锟斤拷systemProperties锟斤拷Bean锟斤拷锟斤拷锟斤拷锟斤拷锟绞癸拷锟� 锟斤拷@systemProperties锟斤拷锟斤拷锟斤拷锟矫革拷Bean
     */
    @Test
    public void testBeanExpression() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext();
        ctx.refresh();
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setBeanResolver(new BeanFactoryResolver(ctx));
        Properties result1 = parser.parseExpression("@systemProperties").getValue(context, Properties.class);
        Assert.assertEquals(System.getProperties(), result1);
    }
    
    @Test
    public void testInnerListExpression() {
        SpelExpressionParser parser = new SpelExpressionParser();
        List<Integer> result1 = parser.parseExpression("{1,2,3}").getValue(List.class);
        Assert.assertEquals(new Integer(1), result1.get(0));
        try {
            result1.set(0, 2);
            //锟斤拷锟斤拷锟斤拷执锟叫碉拷锟解，锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟叫憋拷锟斤拷锟睫革拷
            Assert.fail();
        } catch (Exception e) {
        }
        List<Integer> result2 = parser.parseExpression("{}").getValue(List.class);
        Assert.assertEquals(0, result2.size());
        
        //锟斤拷锟斤拷锟叫憋拷锟斤拷只要锟斤拷一锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟绞斤拷锟斤拷锟街伙拷锟斤拷锟皆糒ist锟斤拷  
        //锟斤拷锟斤拷锟斤拷胁锟斤拷锟斤拷薷拇锟斤拷锟� 
        String expression3 = "{{1+2,2+4},{3,4+4}}";
        List<List<Integer>> result3 = parser.parseExpression(expression3).getValue(List.class);
        result3.get(0).set(0, 1);
        Assert.assertEquals(2, result3.size());
    }
    

    @Test
    public void testInnerArrayExpression() {
        SpelExpressionParser parser = new SpelExpressionParser();
        int[] result1 = parser.parseExpression("new int[1]").getValue(int[].class);
        Assert.assertEquals(1, result1.length);
        int[] result2 = parser.parseExpression("new int[2]{1,2}").getValue(int[].class);
        Assert.assertEquals(2, result2[1]);
        
        String expression3 = "new int[1][2][3]";
        int[][][] result3 = parser.parseExpression(expression3).getValue(int[][][].class);
        Assert.assertEquals(3, result3[0][0].length);

        String expression4 = "new int[1][2][3]{{1}{2}{3}}";
        try {
            int[][][] result4 = parser.parseExpression(expression4).getValue(int[][][].class);
            Assert.fail();
        } catch (Exception e) {
        }
    }    

    @Test
    public void testListAndMapGetValueExpression() {
        SpelExpressionParser parser = new SpelExpressionParser();
        int result1 = parser.parseExpression("{1,2,3}[0]").getValue(int.class);
        //锟斤拷list.get(0)
        Assert.assertEquals(1, result1);
        
        Collection<Integer> collection = new HashSet<Integer>();
        collection.add(1);
        collection.add(2);
        EvaluationContext context2 = new StandardEvaluationContext();
        context2.setVariable("collection", collection);
        int result2 = parser.parseExpression("#collection[1]").getValue(context2, int.class);
        //锟斤拷锟斤拷锟轿何硷拷锟斤拷锟斤拷锟斤拷通锟斤拷Iterator锟斤拷锟斤拷位元锟斤拷
        Assert.assertEquals(2, result2);
        
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("a", 1);
        EvaluationContext context3 = new StandardEvaluationContext();
        context3.setVariable("map", map);
        int result3 = parser.parseExpression("#map['a']").getValue(context3, int.class);
        Assert.assertEquals(1, result3);
    }    

    @Test
    public void testListAndMapSetValueExpression() {
        SpelExpressionParser parser = new SpelExpressionParser();
        
        //1.锟睫革拷锟斤拷锟斤拷元锟斤拷值
        int[] array = new int[] {1, 2};
        EvaluationContext context1 = new StandardEvaluationContext();
        context1.setVariable("array", array);
        int result1 = parser.parseExpression("#array[1] = 3").getValue(context1, int.class);
        Assert.assertEquals(3, result1);
        
        
        //2.锟睫改硷拷锟斤拷值
        Collection<Integer> collection = new ArrayList<Integer>();
        collection.add(1);
        collection.add(2);
        EvaluationContext context2 = new StandardEvaluationContext();
        context2.setVariable("collection", collection);
        int result2 = parser.parseExpression("#collection[1] = 3").getValue(context2, int.class);
        Assert.assertEquals(3, result2);
        parser.parseExpression("#collection[1]").setValue(context2, 4);
        result2 = parser.parseExpression("#collection[1]").getValue(context2, int.class);
        Assert.assertEquals(4, result2);
        
        
        //3.锟睫革拷map元锟斤拷值
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("a", 1);
        EvaluationContext context3 = new StandardEvaluationContext();
        context3.setVariable("map", map);
        int result3 = parser.parseExpression("#map['a'] = 2").getValue(context3, int.class);
        Assert.assertEquals(2, result3);
     
    }    

    @Test
    public void testProjectionExpression() {
        SpelExpressionParser parser = new SpelExpressionParser();
        
        
        //1.锟斤拷锟斤拷准锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
        Collection<Integer> collection = new ArrayList<Integer>();
        collection.add(4);
        collection.add(5);
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("a", 1);
        map.put("b", 2);
            
        //2.锟斤拷锟皆硷拷锟斤拷
        EvaluationContext context1 = new StandardEvaluationContext();
        context1.setVariable("collection", collection);
        Collection<Integer> result1 = parser.parseExpression("#collection.![#this+1]").getValue(context1, Collection.class);
        Assert.assertEquals(2, result1.size());
        Assert.assertEquals(new Integer(5), result1.iterator().next());
        
        
        
        //3.锟斤拷锟斤拷锟街碉拷
        EvaluationContext context2 = new StandardEvaluationContext();
        context2.setVariable("map", map);
        List<Integer> result2 = parser.parseExpression("#map.![value+1]").getValue(context2, List.class);
        Assert.assertEquals(2, result2.size());
    }    

    @Test
    public void testSelectExpression() {
        SpelExpressionParser parser = new SpelExpressionParser();
        
        //1.准锟斤拷锟斤拷锟斤拷
        Collection<Integer> collection = new ArrayList<Integer>();
        collection.add(4);
        collection.add(5);
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("a", 1);
        map.put("b", 2);
        
        //2.锟斤拷锟较伙拷锟斤拷锟斤拷锟斤拷锟�
        EvaluationContext context1 = new StandardEvaluationContext();
        context1.setVariable("collection", collection);
        Collection<Integer> result1 = parser.parseExpression("#collection.?[#this>4]").getValue(context1, Collection.class);
        Assert.assertEquals(1, result1.size());
        Assert.assertEquals(new Integer(5), result1.iterator().next());
        
        //3.锟街碉拷锟斤拷锟�
        EvaluationContext context2 = new StandardEvaluationContext();
        context2.setVariable("map", map);
        Map<String, Integer> result2 = parser.parseExpression("#map.?[#this.key != 'a']").getValue(context2, Map.class);
        Assert.assertEquals(1, result2.size());

        List<Integer> result3 = parser.parseExpression("#map.?[key != 'a'].![value+1]").getValue(context2, List.class);
        Assert.assertEquals(new Integer(3), result3.iterator().next());
    }    

    
    @Test
    public void testTemplateExpression() {
        SpelExpressionParser parser = new SpelExpressionParser();
        ParserContext parserContext = new ParserContext() {
            @Override
            public boolean isTemplate() {
                return true;
            }
            @Override
            public String getExpressionPrefix() {
                return "${";
            }
            @Override
            public String getExpressionSuffix() {
                return "}";
            }
        };
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("v0", 1);
        context.setVariable("v1", 2);
        String result = parser.parseExpression("Error ${#v0} ${#v1}", parserContext).getValue(context, String.class);
        Assert.assertEquals("Error 1 2", result);
        
    }    
    

    
    @Test
    public void testXmlExpression() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("chapter5/el1.xml");
        String hello1 = ctx.getBean("hello1", String.class);
        String hello2 = ctx.getBean("hello2", String.class);
        String hello3 = ctx.getBean("hello3", String.class);
        Assert.assertEquals("Hello World!", hello1);
        Assert.assertEquals("Hello World!", hello2);
        Assert.assertEquals("Hello World!", hello3);
    }    
    

    @Test
    public void testAnnotationExpression() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("chapter5/el2.xml");
        SpELBean helloBean1 = ctx.getBean("helloBean1", SpELBean.class);
        Assert.assertEquals("Hello World!", helloBean1.getValue());

        SpELBean helloBean2 = ctx.getBean("helloBean2", SpELBean.class);
        Assert.assertEquals("haha", helloBean2.getValue());
    }    

    @Test
    public void testPrefixExpression() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("chapter5/el3.xml");
        SpELBean helloBean1 = ctx.getBean("helloBean1", SpELBean.class);
        Assert.assertEquals("#{'Hello' + world}", helloBean1.getValue());
        
        SpELBean helloBean2 = ctx.getBean("helloBean2", SpELBean.class);
        Assert.assertEquals("Hello World!", helloBean2.getValue());
    }    
    

}
