# AmazonBookStorePractice
### Jetpack-Compose를 이용한 선언형 UI, Clean-Architecture에 대한 학습, Test Code에 대한 학습을 위해 </br> Amazon 도서 쇼핑 페이지의 UI를 참고하여 진행한 간단한 프로젝트입니다.

## *****Tech Stack***** 
| 구분 | Tech |
|:---|:---------------------------------------------------------------------------|
| Language | Kotlin |
| Network | Ktor |
| DataBase | Room, DataStore |
| Asynchronous | Coroutine, Flow |
| DI | Dagger-Hilt |
| Serialization | Kotlinx-Serialization |
| Test | JUnit4, Kotlin-Mockito |
| ETC |Jetpack-Compose, Coil |
</br>

## *****Module Dependency Graph***** 
![project dot](https://github.com/user-attachments/assets/62324438-e056-4e93-99d3-322b08fe516f)

</br>

## *****Layer Architecture***** 
![image](https://github.com/user-attachments/assets/beeae049-ce86-49bd-8b56-877c9a415fe5)

</br>

## *****UI Layer***** 
![image](https://github.com/user-attachments/assets/95423d48-7327-49ca-a189-a7743efa27df)
#### ■ State가 Ui elements로 흐르고 State Holder(Viewmodel)로는 Event만 전달되는 단방향 데이터 흐름(UDF)을 적용함.

</br>

## *****Data Flow***** 

</br>

</br>

## *****Video***** 

</br>

## *****Technical Experience*****

## 1) Clean-Architecture를 이용한 레이어 구분
### ■ 문제
- 각 레이어에 맞는 데이터 클래스를 구분하지 않았을 때, 하나의 데이터 클래스에 UI 관련 의존성, 서버 의존성,
DB 의존성 등의 너무 많은 책임이 생겼음, 그로 인해, 각 책임에대한 불필요한 상속 혹은 추가적인 코드가 생겨
코드 수정이 매우 불편해졌음
### ■ 해결
- Clean-Architecture에 맞게 프로젝트 레이어를 Data, Domain, UI 레이어로 분리함. 각 레이어에서 사용할 각
데이터 클래스를 따로 정의하고, 레이어 간에 데이터 이동 시 Mapper를 이용해 각 레이어에 맞는 데이터
타입으로 변환하여 전달하게 수정 함.
- 추가로, UI와 Data 레이어 간의 의존성을 아무런 의존성도 가지지 않는 Domain 레이어로 가지게 되어
Mocking을 이용한 Unit 테스트 작성이 쉬워짐.

</br>

## 2) 테스트 코드를 통한 테스트 자동화
### ■ 문제
- 코드를 리팩토링하거나 새로운 기능을 추가할 때,
기존 기능의 정상 작동 여부를 확인하기 위해 매번 직접 에뮬레이터를 통해 테스트를 해보는 수고가 있었음.
### ■ 해결
- Mockito를 이용한 Mocking객체와 JUnit으로 작성된 UnitTest로 기존에 작성되어있던 코드의 정상 작동
여부를 자동으로 확인할 수 있게되었음.
- JUnit으로 계측 테스트 코드 작성함으로써, 실제로 DataStore에 데이터가 저장되는지 여부도 확인 할 수
있게되었음.

</br>

## 3) State Hoisting과 UDF(단방향 데이터 흐름)
- State Hoisting을 통해 UI가 ViewModel에게 Event를 통해 State를 넘겨 줌으로써, UI상태를 ViewModel에서
관리하고, 갱신된 UiState를 UI에게 전달해주는 단방향 데이터 흐름을 통해 Stateless한 Composable 구성과 각
Event에 맞는 State변경이 이루어 졌는지만 확인하면 되는, 보다 테스트 친화적인 구조에 대해 알게되었음.

</br>

## 4) Jetpack-Compose를 이용한 선언형 UI 구성
- 이벤트가 발생하면, View의 상태 변경 함수를 호출하여, UI를 바꾸는 기존의 xml을 이용한 명령형 UI와 달리,
관찰하던 UI state가 변경되면, 리컴포지션 발생으로 인해 달라진 상태에 알맞은 Composable UI 구성으로 UI를
갱신함에 있어서 차이가 있음을 알게되었음.
- 기존의 List UI를 구성하기 위해서 작성해야했던, RecyclerView와 Adapter 관련 코드들 없이 LazyColumn을
이용하여 쉬운 List UI 구성 방법을 학습함.
- xml을 이용한 명령형 UI에서는 Animation 효과를 주기 위해서 상황에 맞는 xml을 따로 정의하고,
AnimatorInflater를 이용하는 등 구현하기 힘들었던 애니메이션을 AnimatedVisibility와 같은 Composable
함수로 손 쉽게 애니메이션을 적용할 수 있음을 학습함.

</br>

## 5) Composition의 생명주기와 리컴포지션 최적화
- Compose 생명주기를 이해하고 @Immutable, @Stable, ImmutableList, PersistentList를 이용한
리컴포지션 최적화에 대한 개념을 알게되었음.
- Compose compiler를 이용하여 skippable 등의 리컴포지션 최적화 여부를 확인 할 수 있는 방법을 알게되었음.
- Layout Inspector를 이용하여 리컴포지션 디버깅 방법을 학습함.
