package com.pwel.allegrotrader.allegro.domain.search.response.offer;

import java.util.ArrayList;

class Filter {
    String id;
    String type;
    String name;
    ArrayList<Value> values;
    int minValue;
    int maxValue;
    String unit;
}
