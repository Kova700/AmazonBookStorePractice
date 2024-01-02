package com.kova700.amazonbookstorepractice.util

import com.kova700.amazonbookstorepractice.data.response.BookResponse
import com.kova700.amazonbookstorepractice.data.response.NetworkBook
import kotlinx.serialization.json.Json

private val jsonConvertFormat = Json {
	ignoreUnknownKeys = true
	coerceInputValues = true
	encodeDefaults = true
	isLenient = true
}
private const val JSON_MEDIA_TYPE = "application/json"

fun getDummyList(): List<NetworkBook> {
	val responseJsonString = "{\n" +
					"  \"documents\": [\n" +
					"    {\n" +
					"      \"authors\": [\n" +
					"        \"마르친 모스카와\"\n" +
					"      ],\n" +
					"      \"contents\": \"코틀린 코루틴은 효율적이고 신뢰할 수 있는 멀티스레드 프로그램을 쉽게 구현할 수 있게 해 주어 자바 가상 머신(JVM), 특히 안드로이드 및 백엔드 개발 방식을 획기적으로 바꾸어 놓았다. 이 책은 코틀린 전문 강사인 저자가 진행한 워크숍의 참가자들이 궁금해하던 코틀린 코루틴에 대한 모든 것을 담고 있다. 이 책에서는 코틀린 언어에서 자체적으로 지원하는 부분과 kotlinx.coroutines 라이브러리를 모두 사용해 코틀린 코루틴이 어떻게 작동하는지\",\n" +
					"      \"datetime\": \"2023-11-01T00:00:00.000+09:00\",\n" +
					"      \"isbn\": \"8966264158 9788966264155\",\n" +
					"      \"price\": 34000,\n" +
					"      \"publisher\": \"인사이트\",\n" +
					"      \"sale_price\": 30600,\n" +
					"      \"status\": \"정상판매\",\n" +
					"      \"thumbnail\": \"https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F6468627%3Ftimestamp%3D20231116183409\",\n" +
					"      \"title\": \"코틀린 코루틴\",\n" +
					"      \"translators\": [\n" +
					"        \"신성열\"\n" +
					"      ],\n" +
					"      \"url\": \"https://m.search.daum.net/search?w=bookpage&bookId=6468627&q=%EC%BD%94%ED%8B%80%EB%A6%B0+%EC%BD%94%EB%A3%A8%ED%8B%B4\"\n" +
					"    },\n" +
					"    {\n" +
					"      \"authors\": [\n" +
					"        \"고돈호\"\n" +
					"      ],\n" +
					"      \"contents\": \"개발에 익숙한 사람보다는 막연히 개발자가 되고 싶거나, 개발에 대한 배경지식이 없는 입문자를 대상으로 집필하였습니다. 안드로이드 스튜디오 사용법과 코틀린의 기본 문법부터 오픈 API를 활용해 안드로이드 앱을 만들고, 구글 플레이 스토어에 출시하는 과정까지 안드로이드 앱 개발에 필요한 모든 과정을 설명합니다 .  이런 독자에게 권합니다.  ■ 코틀린은 처음이다. 만들고 싶은 앱이 있다. ■ SW 개발자가 되고 싶다. 앱 개발을 잘하고 싶다. ■ 입사 후\",\n" +
					"      \"datetime\": \"2020-05-01T00:00:00.000+09:00\",\n" +
					"      \"isbn\": \"1162242973 9791162242971\",\n" +
					"      \"price\": 34000,\n" +
					"      \"publisher\": \"한빛미디어\",\n" +
					"      \"sale_price\": 30600,\n" +
					"      \"status\": \"정상판매\",\n" +
					"      \"thumbnail\": \"https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F5351475%3Ftimestamp%3D20211212171519\",\n" +
					"      \"title\": \"이것이 안드로이드다 with 코틀린\",\n" +
					"      \"translators\": [],\n" +
					"      \"url\": \"https://m.search.daum.net/search?w=bookpage&bookId=5351475&q=%EC%9D%B4%EA%B2%83%EC%9D%B4+%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C%EB%8B%A4+with+%EC%BD%94%ED%8B%80%EB%A6%B0\"\n" +
					"    },\n" +
					"    {\n" +
					"      \"authors\": [\n" +
					"        \"안귀정\"\n" +
					"      ],\n" +
					"      \"contents\": \"자바와 코틀린으로 직접 구현하며 익히는 실전형 앱 프로그래밍 완벽 가이드!  안드로이드 9.0 파이(Pie), Java+Kotlin, 안드로이드 스튜디오 3.2, 실전형 앱 프로젝트  로또번호생성기, 퀴즈잠금화면앱, 펀치력측정앱, 서울시화장실찾기앱, 익명SNS앱 등    “이런 앱이 있다면 좋지 않을까?”라는 가장 기본적인 아이디어로 시작되는 앱 개발!  하지만 알아야 할 자바(Java) 문법과 개념들로 인해 실제 출시까지의 앱 개발 과정은 생각보다\",\n" +
					"      \"datetime\": \"2019-01-12T00:00:00.000+09:00\",\n" +
					"      \"isbn\": \"1186886897 9791186886892\",\n" +
					"      \"price\": 40000,\n" +
					"      \"publisher\": \"아이콕스\",\n" +
					"      \"sale_price\": 36000,\n" +
					"      \"status\": \"정상판매\",\n" +
					"      \"thumbnail\": \"https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F4851688%3Ftimestamp%3D20231014171723\",\n" +
					"      \"title\": \"안드로이드 with Kotlin 앱 프로그래밍 가이드\",\n" +
					"      \"translators\": [],\n" +
					"      \"url\": \"https://m.search.daum.net/search?w=bookpage&bookId=4851688&q=%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C+with+Kotlin+%EC%95%B1+%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D+%EA%B0%80%EC%9D%B4%EB%93%9C\"\n" +
					"    },\n" +
					"    {\n" +
					"      \"authors\": [\n" +
					"        \"고돈호\"\n" +
					"      ],\n" +
					"      \"contents\": \"최신 버전에 맞춰 완벽하게 실습할 수 있는 코틀린 안드로이드 앱 개발 도서! 코틀린, 안드로이드, 안드로이드 스튜디오! 코틀린으로 안드로이드 앱을 개발하려면 살펴봐야 할 사항이 많습니다. 특히 코틀린 1.4.20 버전부터는 코틀린 익스텐션이 폐기되는 터라 입문자로서 더욱 혼란스럽습니다. 〈이것이 안드로이드다 with 코틀린(개정판)〉은 최신 버전에 대응해 코틀린 익스텐션 코드를 걷어내고 뷰바인딩 코드로 전면 수정했습니다. 물론 안드로이드 스튜디오 4.1\",\n" +
					"      \"datetime\": \"2021-03-02T00:00:00.000+09:00\",\n" +
					"      \"isbn\": \"1162243945 9791162243947\",\n" +
					"      \"price\": 34000,\n" +
					"      \"publisher\": \"한빛미디어\",\n" +
					"      \"sale_price\": 30600,\n" +
					"      \"status\": \"정상판매\",\n" +
					"      \"thumbnail\": \"https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F5602455%3Ftimestamp%3D20220904152402\",\n" +
					"      \"title\": \"이것이 안드로이드다 with 코틀린(이것이 시리즈)\",\n" +
					"      \"translators\": [],\n" +
					"      \"url\": \"https://m.search.daum.net/search?w=bookpage&bookId=5602455&q=%EC%9D%B4%EA%B2%83%EC%9D%B4+%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C%EB%8B%A4+with+%EC%BD%94%ED%8B%80%EB%A6%B0%28%EC%9D%B4%EA%B2%83%EC%9D%B4+%EC%8B%9C%EB%A6%AC%EC%A6%88%29\"\n" +
					"    },\n" +
					"    {\n" +
					"      \"authors\": [\n" +
					"        \"오준석\"\n" +
					"      ],\n" +
					"      \"contents\": \"이 책은 기본을 빠르게 익히고 나서 현업에서 사용하는 라이브러리와 프레임워크로 앱을 효과적으로 만드는 방법을 알려주는 입문 + 활용서입니다. 코틀린을 몰라도, 안드로이드를 몰라도 안드로이드 앱을 만들 수 있습니다. ‘코틀린 입문 + 안드로이드 SDK 입문 + 실전 앱 개발’을 한 권으로 전달하니까요.  또한 시종일관 아주 낮은 눈높이로 설명해 끝까지 책을 완독하는 데 전혀 무리가 없습니다. 이 책에서 제공하는 9가지 앱을 만들다 보면 안드로이드 앱\",\n" +
					"      \"datetime\": \"2018-10-01T00:00:00.000+09:00\",\n" +
					"      \"isbn\": \"1162241195 9791162241196\",\n" +
					"      \"price\": 32000,\n" +
					"      \"publisher\": \"한빛미디어\",\n" +
					"      \"sale_price\": 28800,\n" +
					"      \"status\": \"정상판매\",\n" +
					"      \"thumbnail\": \"https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F3755455%3Ftimestamp%3D20220410091551\",\n" +
					"      \"title\": \"오준석의 안드로이드 생존코딩: 코틀린 편(소문난 명강의)\",\n" +
					"      \"translators\": [],\n" +
					"      \"url\": \"https://m.search.daum.net/search?w=bookpage&bookId=3755455&q=%EC%98%A4%EC%A4%80%EC%84%9D%EC%9D%98+%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C+%EC%83%9D%EC%A1%B4%EC%BD%94%EB%94%A9%3A+%EC%BD%94%ED%8B%80%EB%A6%B0+%ED%8E%B8%28%EC%86%8C%EB%AC%B8%EB%82%9C+%EB%AA%85%EA%B0%95%EC%9D%98%29\"\n" +
					"    },\n" +
					"    {\n" +
					"      \"authors\": [\n" +
					"        \"이현석\"\n" +
					"      ],\n" +
					"      \"contents\": \"이 책에서는 일종의 중개 서비스 플랫폼의 MVP를 안드로이드 앱부터 서버사이드까지 코틀린으로 개발해보면서, 앱 개발에 대해 잘 모르는 백엔드 개발자나 백엔드를 모르는 앱 개발자들을 포함해 하나부터 열까지의 앱 서비스 개발에 대해 궁금한 개발자들로 하여금 서비스 가능한 애플리케이션을 혼자서 만들 수 있도록 각 단에서의 실용적인 기능들을 다루고 있습니다.  안드로이드 앱을 개발하는 과정을 통해서는 기본적인 UI를 구성하는 방법과 사용자 인증 방법, 서버의\",\n" +
					"      \"datetime\": \"2020-05-11T00:00:00.000+09:00\",\n" +
					"      \"isbn\": \"1161340661 9791161340661\",\n" +
					"      \"price\": 26000,\n" +
					"      \"publisher\": \"터닝포인트\",\n" +
					"      \"sale_price\": 23400,\n" +
					"      \"status\": \"정상판매\",\n" +
					"      \"thumbnail\": \"https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F5340301%3Ftimestamp%3D20221003225840\",\n" +
					"      \"title\": \"코틀린으로 쇼핑몰 앱 만들기\",\n" +
					"      \"translators\": [],\n" +
					"      \"url\": \"https://m.search.daum.net/search?w=bookpage&bookId=5340301&q=%EC%BD%94%ED%8B%80%EB%A6%B0%EC%9C%BC%EB%A1%9C+%EC%87%BC%ED%95%91%EB%AA%B0+%EC%95%B1+%EB%A7%8C%EB%93%A4%EA%B8%B0\"\n" +
					"    },\n" +
					"    {\n" +
					"      \"authors\": [\n" +
					"        \"강성윤\"\n" +
					"      ],\n" +
					"      \"contents\": \"이 책은 구글에서 공식 언어로 채택한 코틀린으로 안드로이드 앱을 개발하는 전 과정을 다룬다. IT 업계의 명강사인 깡샘이 입문자도 쉽게 이해할 수 있게 구성하여 이 책만으로도 코틀린 모바일 앱 개발자로 성장할 수 있도록 했다. 특히 우리가 한번쯤 사용해 본 기능들을 실습 주제로 다뤄서 끝까지 흥미를 잃지 않고 학습할 수 있다.  코틀린 문법은 핵심만 담았으며 앱의 첫 화면 설계부터 이벤트 처리, 알림, 데이터베이스, 위치 정보 활용, 네트워킹, 파이어베이스\",\n" +
					"      \"datetime\": \"2021-08-21T00:00:00.000+09:00\",\n" +
					"      \"isbn\": \"1163032735 9791163032731\",\n" +
					"      \"price\": 36000,\n" +
					"      \"publisher\": \"이지스퍼블리싱\",\n" +
					"      \"sale_price\": 32400,\n" +
					"      \"status\": \"정상판매\",\n" +
					"      \"thumbnail\": \"https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F5819417%3Ftimestamp%3D20231108184343\",\n" +
					"      \"title\": \"Do it! 깡샘의 안드로이드 앱 프로그래밍 with 코틀린\",\n" +
					"      \"translators\": [],\n" +
					"      \"url\": \"https://m.search.daum.net/search?w=bookpage&bookId=5819417&q=Do+it%21+%EA%B9%A1%EC%83%98%EC%9D%98+%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C+%EC%95%B1+%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D+with+%EC%BD%94%ED%8B%80%EB%A6%B0\"\n" +
					"    },\n" +
					"    {\n" +
					"      \"authors\": [\n" +
					"        \"박상길\"\n" +
					"      ],\n" +
					"      \"contents\": \"코딩 테스트와 알고리즘 인터뷰를 준비하는 개발자들을 위한 최고의 책 『파이썬 알고리즘 인터뷰』가 자바와 코틀린 코드로 무장하고 한층 업그레이드되어 돌아왔다! 이 책 한 권이면 취업이나 이직 준비는 물론, 현업에서도 바로 활용 가능한 실무 코드를 학습할 수 있는 기본기를 다질 수 있다. 200여 가지가 넘는 풍부한 일러스트를 통해 알고리즘과 자료구조의 기초도 다시 한번 탄탄히 다져보자.  V 취업 준비생과 이직자를 위한 알고리즘과 자료구조 완벽 학습 가이드\",\n" +
					"      \"datetime\": \"2023-09-20T00:00:00.000+09:00\",\n" +
					"      \"isbn\": \"1189909553 9791189909550\",\n" +
					"      \"price\": 42000,\n" +
					"      \"publisher\": \"책만\",\n" +
					"      \"sale_price\": 37800,\n" +
					"      \"status\": \"정상판매\",\n" +
					"      \"thumbnail\": \"https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F6439061%3Ftimestamp%3D20230926153640\",\n" +
					"      \"title\": \"자바 알고리즘 인터뷰 with 코틀린\",\n" +
					"      \"translators\": [],\n" +
					"      \"url\": \"https://m.search.daum.net/search?w=bookpage&bookId=6439061&q=%EC%9E%90%EB%B0%94+%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98+%EC%9D%B8%ED%84%B0%EB%B7%B0+with+%EC%BD%94%ED%8B%80%EB%A6%B0\"\n" +
					"    },\n" +
					"    {\n" +
					"      \"authors\": [\n" +
					"        \"서창준\"\n" +
					"      ],\n" +
					"      \"contents\": \"안드로이드 개발은 어렵고 복잡한 것처럼 느껴지지만, 처음부터 두렵다고 느끼지 마십시오. 『하루 만에 배우는 안드로이드 앱 만들기 with 코틀린』은 프로그래밍 경험이 없거나 초보자를 위한 완벽한 안내서입니다. 이 책은 지루하고 복잡한 내용을 최소화하고, 초보자들이 쉽게 따라 할 수 있도록 구성되었습니다.  안드로이드 앱을 만들기 위해서는 개발에 사용되는 프로그램이나 여러 가지 도구들을 알아야 합니다. 도구에 대한 내용을 이해하고 코틀린이란 언어의\",\n" +
					"      \"datetime\": \"2023-12-05T00:00:00.000+09:00\",\n" +
					"      \"isbn\": \"8931469837 9788931469837\",\n" +
					"      \"price\": 25000,\n" +
					"      \"publisher\": \"영진닷컴\",\n" +
					"      \"sale_price\": 22500,\n" +
					"      \"status\": \"정상판매\",\n" +
					"      \"thumbnail\": \"https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F6502447%3Ftimestamp%3D20231208153428\",\n" +
					"      \"title\": \"하루 만에 배우는 안드로이드 앱 만들기 with 코틀린\",\n" +
					"      \"translators\": [],\n" +
					"      \"url\": \"https://m.search.daum.net/search?w=bookpage&bookId=6502447&q=%ED%95%98%EB%A3%A8+%EB%A7%8C%EC%97%90+%EB%B0%B0%EC%9A%B0%EB%8A%94+%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C+%EC%95%B1+%EB%A7%8C%EB%93%A4%EA%B8%B0+with+%EC%BD%94%ED%8B%80%EB%A6%B0\"\n" +
					"    },\n" +
					"    {\n" +
					"      \"authors\": [\n" +
					"        \"Marco Vermeulen\",\n" +
					"        \"Runar Bjarnason\",\n" +
					"        \"Paul Chiusano\"\n" +
					"      ],\n" +
					"      \"contents\": \"함수형 프로그래밍을 들어본 개발자는 많지만 제대로 된 함수형 프로그래밍을 배우기 위해 하스켈이나 스칼라까지 배울 여유가 없는 독자도 많을 것이다. 이 책은 정석적인 함수형 프로그래밍 책이지만 코틀린을 사용하기 때문에 더 쉽게 함수형 프로그래밍에 접근하고 싶은 독자들에게 도움이 된다. 함수형 프로그래밍을 자신의 개발에 적용하고 싶은 개발자나, 함수형 패러다임을 익혀서 새로운 사고 방식을 익히고 직간접적으로 그 지식을 활용하고 싶은 개발자들이 연습문제\",\n" +
					"      \"datetime\": \"2023-07-31T00:00:00.000+09:00\",\n" +
					"      \"isbn\": \"1161757686 9791161757681\",\n" +
					"      \"price\": 45000,\n" +
					"      \"publisher\": \"에이콘출판\",\n" +
					"      \"sale_price\": 40500,\n" +
					"      \"status\": \"정상판매\",\n" +
					"      \"thumbnail\": \"https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F6388889%3Ftimestamp%3D20231123184626\",\n" +
					"      \"title\": \"코틀린 함수형 프로그래밍\",\n" +
					"      \"translators\": [\n" +
					"        \"오현석\"\n" +
					"      ],\n" +
					"      \"url\": \"https://m.search.daum.net/search?w=bookpage&bookId=6388889&q=%EC%BD%94%ED%8B%80%EB%A6%B0+%ED%95%A8%EC%88%98%ED%98%95+%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D\"\n" +
					"    }\n" +
					"  ],\n" +
					"  \"meta\": {\n" +
					"    \"is_end\": false,\n" +
					"    \"pageable_count\": 153,\n" +
					"    \"total_count\": 153\n" +
					"  }\n" +
					"}"
	return jsonConvertFormat.decodeFromString<BookResponse>(responseJsonString).books
}