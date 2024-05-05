package com.pwel.allegrotrader.allegro.domain.search.response.offer;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Items {
    ArrayList<Promoted> promoted;
    ArrayList<Regular> regular;
}
