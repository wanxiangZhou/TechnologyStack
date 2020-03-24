package com.touchgold.utils.jep;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.singularsys.jep.Jep;
import com.singularsys.jep.JepException;
import com.singularsys.jep.configurableparser.StandardConfigurableParser;

/**
 * @description: 比较运算符、自定义公式计算
 * @author: wanxiangZhou
 * @e-mail: jframe@163.com
 * @date: Created in 2020/2/27 2:42 下午
 */
public class JepDemo {

    public static void main(String[] args) {
        // 方式一：http://www.singularsys.com/jep/doc/html/index.html
        Jep jep = new Jep(new StandardConfigurableParser());
        // 支持更多的操作符
        // jep.setComponents(new StandardConfigurableParser(),new JavaOperatorTable());
        // Jep还支持自定义公式、使用复杂的数学公式.（略）
        // ≦:\u2266 ≤:\u22664
        jep.getOperatorTable().getLE().addAltSymbol("\u2266");
        // jep.getOperatorTable().getLT().addAltSymbol("\u0026\u006c\u0074\u003b");
        jep.reinitializeComponents();
        // 设置公式
        String expression_le = "1 ≦ x && x < 70";
        String expression_lt = "x < 70";
        // 不支持 10%≦ y && y < 70% 这种比较运算
        String expression_le_percent = "0.1≦ y && y < 0.7";
        // 给变量赋值
        try {
            // 有些字符（pi、e、true、false、i）是保留作为默认值的，不能当做变量
            jep.addVariable("x", 100);
            // 运算
            jep.parse(expression_le);
            // 得出结果
            Object le = jep.evaluate();
            System.out.println(le);
            jep.parse(expression_lt);
            Object lt = jep.evaluate();
            System.out.println(lt);
            System.out.println((boolean) le && (boolean) lt);
            jep.addVariable("y", 0.6);
            System.out.println(jep.evaluate(jep.parse(expression_le_percent)));
        } catch (JepException e) {
            e.printStackTrace();
        }
        // 方式二：
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        try {
            Object result1 = engine.eval("1 <= 3 < 5");
            System.out.println(result1);
            Object result2 = engine.eval("1 == 2");
            System.out.println(result2);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}
