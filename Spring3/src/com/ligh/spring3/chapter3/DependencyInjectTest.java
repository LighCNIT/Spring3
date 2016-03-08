package com.ligh.spring3.chapter3;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ligh.spring3.chapter2.helloworld.HelloApi;
import com.ligh.spring3.chapter3.bean.ArrayTestBean;
import com.ligh.spring3.chapter3.bean.BooleanTestBean;
import com.ligh.spring3.chapter3.bean.CollectionTestBean;
import com.ligh.spring3.chapter3.bean.IdRefTestBean;
import com.ligh.spring3.chapter3.bean.ListTestBean;
import com.ligh.spring3.chapter3.bean.MapTestBean;
import com.ligh.spring3.chapter3.bean.NavigationA;
import com.ligh.spring3.chapter3.bean.NavigationC;
import com.ligh.spring3.chapter3.bean.PropertiesTestBean;
import com.ligh.spring3.chapter3.bean.SetTestBean;
public class DependencyInjectTest {
    
	//构造器注入方式测试方法
    @Test
    public void testConstructorDependencyInject() {
        
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("chapter3/constructorDependencyInject.xml");
        
        //获取根据参数索引依赖注入的Bean
        HelloApi byIndex = beanFactory.getBean("byIndex", HelloApi.class);
        byIndex.sayHello();

        //获取根据参数类型依赖注入的Bean
        HelloApi byType = beanFactory.getBean("byType", HelloApi.class);
        byType.sayHello();

        //获取根据参数名依赖注入的Bean
        HelloApi byName = beanFactory.getBean("byName", HelloApi.class);
        byName.sayHello();
        
    }

    //静态工厂方式注入测试方法
    @Test
    public void testStaticFactoryDependencyInject() {
        
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("chapter3/staticFactoryDependencyInject.xml");
        
        //获取根据参数索引依赖注入的Bean
        HelloApi byIndex = beanFactory.getBean("byIndex", HelloApi.class);
        byIndex.sayHello();
        
        //获取根据参数类型依赖注入的Bean
        HelloApi byType = beanFactory.getBean("byType", HelloApi.class);
        byType.sayHello();
        
        //获取根据参数名依赖注入的Bean
        HelloApi byName = beanFactory.getBean("byName", HelloApi.class);
        byName.sayHello();
        
    }

    //实例化工厂注入测试方法
    @Test
    public void testInstanceFactoryDependencyInject() {
        
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("chapter3/instanceFactoryDependencyInject.xml");
        
        
        HelloApi byIndex = beanFactory.getBean("byIndex", HelloApi.class);
        byIndex.sayHello();
        
        
        HelloApi byType = beanFactory.getBean("byType", HelloApi.class);
        byType.sayHello();
        
        
        HelloApi byName = beanFactory.getBean("byName", HelloApi.class);
        byName.sayHello();
        
    }
    
    @Test
    public void testSetterDependencyInject() {
        
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("chapter3/setterDependencyInject.xml");
        
        HelloApi bean = beanFactory.getBean("bean", HelloApi.class);
        bean.sayHello();
    }

    
    @Test
    public void testBooleanInject() {
        
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("chapter3/booleanInject.xml");
        
        BooleanTestBean bean1 = beanFactory.getBean("bean1", BooleanTestBean.class);
        System.out.println(bean1.isSuccess());

        BooleanTestBean bean2 = beanFactory.getBean("bean2", BooleanTestBean.class);
        System.out.println(bean2.isSuccess());

        BooleanTestBean bean3 = beanFactory.getBean("bean3", BooleanTestBean.class);
        System.out.println(bean3.isSuccess());

        BooleanTestBean bean4 = beanFactory.getBean("bean4", BooleanTestBean.class);
        System.out.println(bean4.isSuccess());
    }

    
    @Test
    public void testIdRefInject() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("chapter3/idRefInject.xml");

        IdRefTestBean bean1 = beanFactory.getBean("idrefBean1", IdRefTestBean.class);
        System.out.println(bean1.getId());
        
        IdRefTestBean bean2 = beanFactory.getBean("idrefBean2", IdRefTestBean.class);
        System.out.println(bean2.getId());
    }

    
    @Test
    public void testListInject() {
        
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("chapter3/listInject.xml");
        ListTestBean listBean = beanFactory.getBean("listBean", ListTestBean.class);
        System.out.println(listBean.getValues().size());
        Assert.assertEquals(3, listBean.getValues().size());
        
    }

    
    @Test
    public void testSetInject() {
        
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("chapter3/setInject.xml");
        SetTestBean setBean = beanFactory.getBean("setBean", SetTestBean.class);
        System.out.println(setBean.getValues().size());
        Assert.assertEquals(3, setBean.getValues().size());
        
    }
    

    
    @Test
    public void testCollectionInject() {
        
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("chapter3/collectionInject.xml");
        
        
        CollectionTestBean collectionBean1 = beanFactory.getBean("collectionBean1", CollectionTestBean.class);
        System.out.println("Type:" + collectionBean1.getValues().getClass().getName());
        System.out.println(collectionBean1.getValues().size());
        Assert.assertEquals(3, collectionBean1.getValues().size());

        
        CollectionTestBean collectionBean2 = beanFactory.getBean("collectionBean2", CollectionTestBean.class);
        System.out.println("Type:" + collectionBean2.getValues().getClass().getName());
        System.out.println(collectionBean2.getValues().size());
        Assert.assertEquals(3, collectionBean2.getValues().size());
        
    }

    
    @Test
    public void testArrayInject() {
        
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("chapter3/arrayInject.xml");
        ArrayTestBean arrayBean = beanFactory.getBean("arrayBean", ArrayTestBean.class);
        System.out.println(arrayBean.getArray().length);
        System.out.println(arrayBean.getArray2().length);
        System.out.println(arrayBean.getArray2()[0].length);
        System.out.println(arrayBean.getArray2()[1].length);
        
    }

    
    @Test
    public void testMapInject() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("chapter3/mapInject.xml");
        MapTestBean mapBean = beanFactory.getBean("mapBean", MapTestBean.class);
        System.out.println(mapBean.getValues().size());
        System.out.println(mapBean.getValues());        
    }

    
    
    @Test
    public void testPropertiesInject() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("chapter3/propertiesInject.xml");
        PropertiesTestBean propertiesBean = beanFactory.getBean("propertiesBean", PropertiesTestBean.class);
        System.out.println(propertiesBean.getValues().size());
        System.out.println(propertiesBean.getValues().containsValue("22"));

        PropertiesTestBean propertiesBean2 = beanFactory.getBean("propertiesBean2", PropertiesTestBean.class);
        System.out.println(propertiesBean2.getValues().size());
        System.out.println(propertiesBean2.getValues().containsKey("1"));
        System.out.println(propertiesBean2.getValues().containsKey("2"));
        System.out.println(propertiesBean2.getValues().containsKey("3"));
        System.out.println(propertiesBean2.getValues().containsKey("4"));
        System.out.println(propertiesBean2.getValues().containsKey("5"));
        System.out.println(propertiesBean2.getValues().containsValue("11"));
    }
    
    
    @Test
    public void testBeanInject() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("chapter3/beanInject.xml");
       
        HelloApi bean1 = beanFactory.getBean("bean1", HelloApi.class);
        bean1.sayHello();
        
        HelloApi bean2 = beanFactory.getBean("bean2", HelloApi.class);
        bean2.sayHello();
    }


    
    @Test
    public void testLocalAndparentBeanInject() {
        //初始化父类容器
        ApplicationContext parentBeanContext = new ClassPathXmlApplicationContext("chapter3/parentBeanInject.xml");
        //初始化当前容器
        ApplicationContext beanContext = new ClassPathXmlApplicationContext(new String[] {"chapter3/localBeanInject.xml"}, parentBeanContext);
        //引用local bean
        HelloApi bean1 = beanContext.getBean("bean1", HelloApi.class);
        bean1.sayHello();
        //引用parent bean
        HelloApi bean2 = beanContext.getBean("bean2", HelloApi.class);
        bean2.sayHello();
    }

    
    @Test
    public void testInnerBeanInject() {
        ApplicationContext context = new ClassPathXmlApplicationContext("chapter3/innerBeanInject.xml");

        HelloApi bean = context.getBean("bean", HelloApi.class);
        bean.sayHello();
        
    }

    //
    @Test
    public void testNavigationBeanInject() {
        ApplicationContext context = new ClassPathXmlApplicationContext("chapter3/navigationBeanInject.xml");
        
       
        NavigationA navigationA = context.getBean("a", NavigationA.class);
        
        navigationA.getNavigationB().getNavigationC().sayNavigation();
        
        navigationA.getNavigationB().getList().get(0).sayNavigation();
        
        navigationA.getNavigationB().getMap().get("key").sayNavigation();
        
        navigationA.getNavigationB().getArray()[0].sayNavigation();
        
        ((NavigationC)navigationA.getNavigationB().getProperties().get("1")).sayNavigation();
        
        
    }

    @Test
    public void testPNamespaceBeanInject() {
        ApplicationContext context = new ClassPathXmlApplicationContext("chapter3/pNamespaceInject.xml");
        Assert.assertEquals("value", context.getBean("idrefBean1", IdRefTestBean.class).getId());
        Assert.assertEquals("test", context.getBean("idrefBean2", IdRefTestBean.class).getId());
        
    }
}
