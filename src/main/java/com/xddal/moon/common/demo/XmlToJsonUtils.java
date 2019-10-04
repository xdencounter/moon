package com.xddal.moon.common.demo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.apache.log4j.Logger;
import org.dom4j.*;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

/**
 * @author xuedong
 */
public class XmlToJsonUtils {
    private static Logger logger = Logger.getLogger(XmlToJsonUtils.class);

    public static void main(String[] args) throws Exception {
        // String xmlStr= readFile("D:/ADA/et/Issue_20130506_back.xml");

        String xmlStr = "<auth_response status=\"OK\" xmlns=\"http://www.fnac.com/schemas/mp-dialog.xsd\">"
                + "<token>00E9822E-E926-C274-53BF-A2784195A3CE</token>"
                + "<validity>2018-03-23T10:45:21+01:00</validity>" + "<version>2.6.0</version>" + "</auth_response>";
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "<batch_status_response status=\"OK\">"
                + "<batch_id>DC3F089A-8E98-4B98-9434-0DF0EE937DC8</batch_id>" + "<offer status=\"OK\">"
                + "<product_fnac_id>2499187</product_fnac_id>"
                + "<offer_fnac_id>E473361D-683B-2A26-E878-F43816B9E111</offer_fnac_id>"
                + "<offer_seller_id>561C-385-9BE</offer_seller_id>" + "</offer>" + "<offer status=\"OK\">"
                + "<product_fnac_id>9784515</product_fnac_id>"
                + "<offer_fnac_id>4EF5F02D-8A87-9502-95F5-14881CDEEF8A</offer_fnac_id>"
                + "<offer_seller_id>B067-F0D-75E</offer_seller_id>" + "</offer>" + "<offer status=\"OK\">"
                + "<product_fnac_id>20005761</product_fnac_id>"
                + "<offer_fnac_id>B42974D3-D5E0-DAF1-629C-60541775D944</offer_fnac_id>"
                + "<offer_seller_id>B76A-CD5-153</offer_seller_id>" + "</offer>" + "</batch_status_response>";

        Document doc = DocumentHelper.parseText(xml);
        JSONObject json = new JSONObject();
        dom4j2Json(doc.getRootElement(), json);
        System.out.println("xml2Json:" + json.toJSONString());

    }

    /**
     *
     * @param xml
     * @return
     */
    public static String xmlToJson(String xml) {
        Document doc;
        try {
            doc = DocumentHelper.parseText(xml);
            JSONObject json = new JSONObject();
            dom4j2Json(doc.getRootElement(), json);
            System.out.println("xml2Json:" + json.toJSONString());
            logger.warn("xml2Json:" + json.toJSONString());
            return json.toJSONString();
        } catch (DocumentException e) {
            logger.warn("数据解析失败");
        }
        return null;

    }

    /**
     *
     * @param path
     * @return
     * @throws Exception
     */
    public static String readFile(String path) throws Exception {
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        FileChannel fc = fis.getChannel();
        ByteBuffer bb = ByteBuffer.allocate(new Long(file.length()).intValue());
        // fc向buffer中读入数据
        fc.read(bb);
        bb.flip();
        String str = new String(bb.array(), "UTF8");
        fc.close();
        fis.close();
        return str;

    }

    /**
     * xml转json
     *
     * @param xmlStr
     * @return
     * @throws DocumentException
     */
    public static JSONObject xml2Json(String xmlStr) throws DocumentException {
        Document doc = DocumentHelper.parseText(xmlStr);
        JSONObject json = new JSONObject();
        dom4j2Json(doc.getRootElement(), json);
        return json;
    }

    /**
     * xml转json
     *
     * @param element
     * @param json
     */
    public static void dom4j2Json(Element element, JSONObject json) {
        // 如果是属性
        for (Object o : element.attributes()) {
            Attribute attr = (Attribute) o;
            if (!isEmpty(attr.getValue())) {
                json.put("@" + attr.getName(), attr.getValue());
            }
        }
        List<Element> chdEl = element.elements();
        if (chdEl.isEmpty() && !isEmpty(element.getText())) {// 如果没有子元素,只有一个值
            json.put(element.getName(), element.getText());
        }

        for (Element e : chdEl) {// 有子元素
            if (!e.elements().isEmpty()) {// 子元素也有子元素
                JSONObject chdjson = new JSONObject();
                dom4j2Json(e, chdjson);
                Object o = json.get(e.getName());
                if (o != null) {
                    JSONArray jsona = null;
                    if (o instanceof JSONObject) {// 如果此元素已存在,则转为jsonArray
                        JSONObject jsono = (JSONObject) o;
                        json.remove(e.getName());
                        jsona = new JSONArray();
                        jsona.add(jsono);
                        jsona.add(chdjson);
                    }
                    if (o instanceof JSONArray) {
                        jsona = (JSONArray) o;
                        jsona.add(chdjson);
                    }
                    json.put(e.getName(), jsona);
                } else {
                    if (!chdjson.isEmpty()) {
                        json.put(e.getName(), chdjson);
                    }
                }

            } else {// 子元素没有子元素
                for (Object o : element.attributes()) {
                    Attribute attr = (Attribute) o;
                    if (!isEmpty(attr.getValue())) {
                        json.put("@" + attr.getName(), attr.getValue());
                    }
                }
                if (!e.getText().isEmpty()) {
                    json.put(e.getName(), e.getText());
                }
            }
        }
    }

    public static boolean isEmpty(String str) {

        if (str == null || str.trim().isEmpty() || "null".equals(str)) {
            return true;
        }
        return false;
    }
}
