package io.namoosori.travelclub.spring.service.logic;

import io.namoosori.travelclub.spring.aggregate.club.TravelClub;
import io.namoosori.travelclub.spring.service.ClubService;
import io.namoosori.travelclub.spring.service.sdo.TravelClubCdo;
import io.namoosori.travelclub.spring.shared.NameValueList;
import io.namoosori.travelclub.spring.store.ClubMapStore;
import io.namoosori.travelclub.spring.store.ClubStore;

import java.util.List;

public class ClubServiceLogic implements ClubService {

    //field 선언
    private ClubStore clubStore;
    // 생성자 만듦
    public  ClubServiceLogic(){
        this.clubStore = new ClubMapStore(); //실제로 알아야하는것 => ClubMapStore
        // clubStore = ClubMapStore의 instance인스턴스
    }


    @Override
    public String registerClub(TravelClubCdo club) {
        clubStore.create();
        return null;
    }

    @Override
    public TravelClub findClubById(String id) {
        return null;
    }

    @Override
    public List<TravelClub> findClubsByName(String name) {
        return null;
    }

    @Override
    public void modify(String clubId, NameValueList nameValues) {

    }

    @Override
    public void remove(String clubId) {

    }
}
