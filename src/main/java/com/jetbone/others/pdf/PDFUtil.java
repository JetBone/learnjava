package com.jetbone.others.pdf;

import java.util.List;

/**
 * Created by Chris on 2020/3/27
 */
public class PDFUtil {

    private List dataList;

    private Class type;

    private PDFUtil(List dataList, Class type) {
        this.dataList = dataList;
        this.type = type;
    }

    public static PDFUtil of(List dataList, Class type) {
        return new PDFUtil(dataList, type);
    }

    public List getDataList() {
        return dataList;
    }

    public Class getType() {
        return type;
    }
}
