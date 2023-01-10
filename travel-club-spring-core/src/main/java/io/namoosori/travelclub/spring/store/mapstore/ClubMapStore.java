package io.namoosori.travelclub.spring.store.mapstore;

import io.namoosori.travelclub.spring.aggregate.club.TravelClub;
import io.namoosori.travelclub.spring.store.ClubStore;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("clubstore")
public class ClubMapStore implements ClubStore {
//    TravelClub을 Map(linkedHashMap)에 저장
    private Map<String, TravelClub> clubMap;

    public ClubMapStore(){
        this.clubMap = new LinkedHashMap<>();
    }

    @Override
    public String create(TravelClub club) {
        // create : 파라메터를 새로 저장해야하는 'TravelClub club'정보가 넘어옴
        // -> 그 클럽을 clubMap에 put해서 저장

        clubMap.put(club.getId(), club);
        return club.getId();
    }

    @Override
    public TravelClub retrieve(String clubId) {
        return clubMap.get(clubId);
    }

    @Override
    public List<TravelClub> retrieveByName(String name) {
        // list 형태 ? =>  동일한 이름의 club이 존재할 수 있다, 만약 동일한 이름이라면 모두 반환하겠다

        // 방법1. for문 이용해서
        // 방법2. Iterator 이용해서 Iterator 돌면서 동일 이름 찾아서 리턴
        // 방법 3. Java8(JDK8)버전부터 제공되고 있는 Collection의 stream 이용 -> 더욱 직관적으로 리스트 리턴 가능

        /*
         clubMap으로 하여금 value에 대한 Colletcion을 얻은 다음 steam얻어 오기
         club의 이름으로 filtering 하는 람다식
         나온 결과는 Collecting해서 list로 반환
        */
        return clubMap.values().stream()
                .filter(club -> club.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public void update(TravelClub club) {
        clubMap.put(club.getId(), club );
    }

    @Override
    public void delete(String clubId) {
        clubMap.remove(clubId);
    }

    @Override
    // 해당 club id를 가지고 있는지 보는 코드
    public boolean exists(String clubId) {
        //return clubMap.containsKey(clubId); -> Map에 clubId를 가지고 Map에 집어넣기에 이렇게 사용가능 :: 이 key를 포함하고 있니?
        return Optional.ofNullable(clubMap.get(clubId)).isPresent();
    }
}
