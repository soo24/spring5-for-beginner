package io.namoosori.travelclub.spring.service.logic;

import io.namoosori.travelclub.spring.TravelClubApp;
import io.namoosori.travelclub.spring.aggregate.club.TravelClub;
import io.namoosori.travelclub.spring.service.ClubService;
import io.namoosori.travelclub.spring.service.sdo.TravelClubCdo;
import io.namoosori.travelclub.spring.shared.NameValueList;
import io.namoosori.travelclub.spring.store.ClubStore;
import io.namoosori.travelclub.spring.util.exception.NoSuchClubException;
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
        return clubStore.retrieve(id); // map에 등록된 해당 id의 객체를 찾아서 등록
    }

    @Override
    public List<TravelClub> findClubsByName(String name) {
        return clubStore.retrieveByName(name);
    }

    @Override
    // NameValueList : 어떤 값들이 바뀌어야되는지에 대한 값이 들어있는 list
    public void modify(String clubId, NameValueList nameValues) {
        // -> 해당 clubID의 클럽을 찾고 -> NameValueList 값을 가지고 해당 데이터의 값을 변경해줌
        TravelClub foundedClub = clubStore.retrieve(clubId); //클럽 찾기
        if(foundedClub == null){
            throw new NoSuchClubException("No such club with id : " + clubId);
        }
        foundedClub.modifyValues(nameValues);
        clubStore.update(foundedClub); // 값이 변경된 club을 보내서 Map에 있는 데이터도 변경될 수 있게끔
    }

    @Override
    public void remove(String clubId) {
        if(!clubStore.exists(clubId)){
            throw new NoSuchClubException("No such club with id : " + clubId);
        }
        clubStore.delete(clubId);
    }
}
