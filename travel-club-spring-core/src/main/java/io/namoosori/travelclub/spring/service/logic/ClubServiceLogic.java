package io.namoosori.travelclub.spring.service.logic;

import io.namoosori.travelclub.spring.TravelClubApp;
import io.namoosori.travelclub.spring.aggregate.club.TravelClub;
import io.namoosori.travelclub.spring.service.ClubService;
import io.namoosori.travelclub.spring.service.sdo.TravelClubCdo;
import io.namoosori.travelclub.spring.shared.NameValueList;
import io.namoosori.travelclub.spring.store.mapstore.ClubMapStore;
import io.namoosori.travelclub.spring.store.ClubStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("clubService")
public class ClubServiceLogic implements ClubService {

    //field 선언
    private ClubStore clubStore;
    // 생성자 만듦
    /*
    기존의 생성자 생성
    public  ClubServiceLogic(){
        this.clubStore = new ClubMapStore(); //실제로 알아야하는것 => ClubMapStore
        // clubStore = ClubMapStore의 instance인스턴스
    }
    */

    /* 빈객체 이용해서 생성자 생성 */
    public  ClubServiceLogic(ClubStore clubStore){
        this.clubStore = clubStore;
    }


    @Override
    public String registerClub(TravelClubCdo club) {
        TravelClub newClub = new TravelClub(club.getName(), club.getIntro());
        newClub.checkValidation(); // TravelClub 클래스에 나와있듯, 클럽이름이 3글자 미만 Or 클럽의 소개가 10글자 미만이면 클럽생성 X해주는 메소드
        return clubStore.create(newClub);
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
