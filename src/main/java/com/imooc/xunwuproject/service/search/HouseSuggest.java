package com.imooc.xunwuproject.service.search;

/**
 * 设置搜索提示属性
 */
public class HouseSuggest {
    private String input;
    private int weight = 10;//权重，默认为10

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
