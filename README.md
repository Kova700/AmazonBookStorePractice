# AmazonBookStorePractice
### 선언형 UI, Clean-Architecture, Test Code, Multi-Module에 대해 공부해보자.

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
Clean-Architecture의 Presentation(UI), Domain, Data 형식의 레이어 구분을 차용하여
featrue:featrue(UI), core:data:feature:external(Domain) , core:data:feature:internal(Data) 형식으로  
각 레이어를 Feature별로 구분하여 모듈을 분리해 해당 Feature를 제거하거나 수정할 때,  
최대한 수정의 여파가 가능한 적은 구조로 모듈을 구성  
</br>
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
![image](https://github.com/user-attachments/assets/3bccbf96-9fd8-490e-a63a-7df755973200)

</br>

## *****Video***** 
|Search & Paging|Search Option|Search History|
|:-----:|:-----:|:-----:|
|<img width="240" src="https://github.com/user-attachments/assets/130b4506-dee1-417a-88f0-f9de86f5a5df">|<img width="240" src="https://github.com/user-attachments/assets/3f2ebd5c-24de-4c5a-8816-dde4b2fde524">|<img width="240" src="https://github.com/user-attachments/assets/3a3315ef-94fa-4970-a8ee-fffbf559e290">|

</br>

|Paging Fail Retry|Expanding Animation|DetailScren|DetailWebView|
|:-----:|:-----:|:-----:|:-----:|
|<img width="240" src="https://github.com/user-attachments/assets/017310d8-9753-447d-bb24-7b5720f5ca89">|<img width="240" src="https://github.com/user-attachments/assets/cea9a938-a294-44c0-9fc5-805b169923ab">|<img width="240" src="https://github.com/user-attachments/assets/2f5f1b26-c7f2-43b6-9c3f-adeb77d27562">|<img width="240" src="https://github.com/user-attachments/assets/fd58faed-03f2-40f1-9f3b-a6f620e2221b">

</br>

## *****Package Structure***** 
```
├── app
├── buildSrc
│   ├── convention
│   └── gradle.configure
├── core
│   ├── data
│   │   ├── booksearch
│   │   │   ├── external
│   │   │   │   ├── model
│   │   │   │   └── repository
│   │   │   └── internal
│   │   │   │   ├── di
│   │   │   │   ├── mapper
│   │   │   │   └── repository
│   │   └── searchhistory
│   │   │   ├── external
│   │   │   │   └── repository
│   │   │   └── internal
│   │   │   │   ├── di
│   │   │   │   └── repository
│   ├── datastore
│   │   ├── datastore
│   │   │   └── di
│   │   └── searchhistory
│   ├── design-system
│   └── network
│       ├── booksearch
│       │   ├── di
│       │   └── model
│       └── network
└── feature
    ├── main
    ├── search
    └── searchhistory
```
</br>

## *****Technical Experience*****

## 1) Clean-Architecture를 이용한 레이어 구분
### ■ 문제
- 각 레이어에 맞는 데이터 클래스를 구분하지 않았을 때, 하나의 데이터 클래스에 UI 관련 의존성, Network 의존성, DB 의존성 등의 너무 많은 책임이 생겼음, 그로 인해, 각 책임에대한 불필요한 상속 혹은 추가적인 코드가 생겨 코드 수정이 매우 불편해졌음. 또한, 새로운 기능을 추가하거나 기존 기능을 제거할 때, 프로젝트 전체에 걸쳐 영향이 미치면서 개발이 복잡해졌음.
### ■ 해결
- Clean-Architecture에 맞게 프로젝트 레이어를 Data, Domain, UI 레이어로 분리함. 
또한, 각 레이어를 다시 Feature별로 나누어 모듈화함으로써 각 기능이 독립적으로 동작할 수 있도록 하였음.
이를 통해 특정 기능의 추가 및 제거가 용이해졌으며, 다른 기능에 미치는 영향을 최소화할 수 있었음.
- 각 레이어에서 사용할 각 데이터 클래스를 따로 정의하고, 레이어 간에 데이터 이동 시 Mapper를 이용해 각 레이어에 맞는 데이터 타입으로 변환하여 전달하게 수정 함.
- 추가로, UI 레이어와 Data 레이어 간의 의존성을 Domain 레이어로 가지게 되어서, 
Mocking을 이용해 각 구현체에 대한 Unit 테스트 작성이 쉬워짐. 

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

## 3) Gradle Version Catalog와 Precompiled Script Plugin을 활용한 빌드 관리 최적화
### ■ 문제
- 프로젝트 내 라이브러리 버전 관리와 빌드 스크립트 관리가 체계적이지 않아, 유지보수 시 비효율적이고 오류가 발생하기 쉬웠음. 특히, 각 모듈마다 중복된 라이브러리 선언과 안드로이드 세팅으로 인해 버전 불일치 문제가 발생함.
### ■ 해결
- Gradle Version Catalog를 도입하여 프로젝트 전반에 걸친 라이브러리 버전 관리를 일원화함. 이를 통해 각 모듈에서 동일한 버전을 쉽게 참조할 수 있게 되어, 버전 불일치 문제를 해결함. 또한, Precompiled Script Plugin을 사용해 빌드 스크립트를 모듈화하고 재사용성을 높여, 빌드 설정 관리를 간소화시킴.

</br>

## 3) State Hoisting과 UDF(단방향 데이터 흐름)
- State Hoisting과 UDF(단방향 데이터 흐름)을 통해 UI elements는 ViewModel에게 Event를 전달하고 StateHolder는 UI elements에게 State를 제공해주는 단방향 데이터 흐름을 통해 Stateless한 Composable 구성과 각 Event에 맞는 State변경이 이루어 졌는지만 확인하면 되는, 보다 테스트 친화적인 구조에 대해 알게되었음.

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
