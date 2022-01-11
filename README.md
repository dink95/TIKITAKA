# TIKITAKA
경매기능 포함 중고거래



DB : MySQL<br/>
Framework : spring boot<br/>
Language : js, java, html, xml <br/>
Library : jquery, stomp & socket js, jwt, Mybatis, kakaoAPI(Map) ..<br/>


구조 형식<br/>
![image](https://user-images.githubusercontent.com/61407645/144394591-444fc4c6-3183-42b0-b591-5374947d80ff.png)


인증 & 인가 구현 -  로그인 시, JWT TOKEN 발급 및 gateway filter를 통해 검증 <br/>
경매기능 구현 - Socket JS 및 stomp 사용<br/>
개선필요=> 서비스 단에서 구현하려 했지만 데이터가 넘어가지 않음 <br/>
대화 구현 - DB table에 readCount를 두고, 비동기 통신을 통해서 짧은 시간 지속적인 확인하는 방식 사용


구현 자료 영상 - https://youtu.be/NNqLQjOLVHk 
