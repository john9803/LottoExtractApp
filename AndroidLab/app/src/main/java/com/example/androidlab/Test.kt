package com.example.androidlab

// val data4: Int  // 함수,클래스내부가 아닌 코드 최상단에 선언되는 변수들은 선언즉시 초기화를 해주어야 한다.

fun main() {

    // =================================================================
    // 1. 변수
    // =================================================================

    // 변수 선언
    val iternalVal = 100;    // 값 변경 불가능
    var nonIternalVal = 100; // 값 변경 가능

    // 타입없는 변수선언
    val data1 = 10;
    // 타입지정 변수선언
    val data2: Int = 20;

    // # 주의 # 아래의 경우에는 변수선언이 성립하지 않는다.
    val data3: Int
    println("hello world")

    println("data1 : $data1 , data2: $data2")

    val data5: Int // 함수 내부의 변수는 꼭 선언할때 초기화 하지 않아도 된다.

    // 초기화 미루기

    // lateinit 키워드로 초기화 미루기

    // 1. var로 선언한 변수만 사용가능
    // 2. Int, Long, Short, Double, Float, Boolean, Byte 타입에서는 사용불가 -> String타입에서 주로사용

    // 불가: Int 타입에는 해당 키워드 사용불가
    // lateinit var data6: Int
    // 불가: val 타입에는 해당 키워드 사용불가
    // lateinit val data7: String
    // 가능
    lateinit var data8: String

    // lazy 키워드로 초기화 미루기

    // 1. 변수 선언문 뒤에 by lazy { } 형식으로 선언하며, 소스코드에서 변수가 최초로 이용되는 순간 중괄호로 묶은 부분 자동실행
    // 2. 그 결과값이 변수의 초깃값으로 할당. lazy 문이 여러줄이라면 마지막 줄의 실행 결과가 변수의 초깃값이 된다.

    val data9: Int by lazy {
        println("in lazy.....") // 최초 변수사용시 구문실행
        10 // 최초 변수사용시 변수의 초갓값으로 할당
    }

    println("in main.....")
    println(data9+10)
    println(data9+10)

    // =================================================================
    // 2. 데이터 타입
    // =================================================================

    // 1. 코틀린의 변수는 모두 객체라는 것을 알고있자. 그말인즉 코틀린의 모튼타입은 객체 타입니다.
    // 예) 정수를 다루는 타입 Int 는 primitive type이 아니고 클래스다.

    fun someFun(){
        var data1: Int = 10
        var data2: Int? = null // null을 대입하는것이 가능하다 (null: 객체만 선언되고 메모리상에 할당이 되지 않은 상태)

        data1 += 10
        data1 = data1.plus(10) // 객체의 메서드 이용가능 -> Int 타입에 plus 더하기 메소드 사용가능
    }


    // 기초 타입 객체 - 정수(Int, Short, Long) | 실수(Double, Float) | 2진수(Byte) | 참/거짓(Boolean)
    val a1: Byte = 0b00001011

    val a2: Int = 123
    val a3: Short = 123
    val a4: Long = 10L
    val a5: Double = 10.0
    val a6: Float = 10.0f

    val a7: Boolean = true

    // 문자와 문자열 - 문자(char)[' 작은 따옴표로 표현] | 문자열(String)[",""" 큰따옴표나 삼중따옴표로 표현]

    // Char
    // 1. Char는 '' 작은따옴표로 감싸서 선언해야함.
    // 2. Number로 취급할 수 없다.

    val a: Char = 'a'

    // 이런식으로 Number의 형식으로 판단할 수 없음
    // if (a==1){ }

    // String
    // 1. String은 "" 큰따옴표, """ """ 삼중따옴표로 표현
    // 2. 큰따옴표안에 표현에서 줄바꿈이나 Tab을 그대로 적용하려면 이스케이프코드를 사용해야 한단 줄바꿈(\n), tab(\t)
    // 2-1. #중요# 삼중따옴표 안에 표현된 String은 이스케이프 코드를 사용하지 않아도 줄바꿈, tab등의 설정이 그대로 적용됨


    val str1 = "Hello \n World"
    val str2 = """
        Hello
        World
    """
    println("str1: $str1")
    println("str2: $str2")

    // Any - 모든 타입 가능
    // 1. Any는 코틀린 최상위 클래스로 모든 코틀린의 클래스는 Any의 하위 클래스임.
    // 2. 고로 Any타입으로 선언한 변수에는 모든 타입의 데이터를 할당할 수 있다.
    fun anyPractice(){
        val data1: Any = 10
        val data2: Any = "hello"

        class User
        val data3: Any= User()
    }


    // Unit - 반환문이 없는 함수 (java의 void와 비슷한듯 )

    // 1. 데이터의 형식이 아닌 특수한 상황을 표현하려는 목적으로 사용함.
    // 2. Unit타입에는 Unit 객체만 대입가능. 사실상 함수의 반환타입을 나타내려는 의도로 사용함.
    // 3. 기본적으로 함수의 반환형이 지정되어 있지 않으면 Unit형으로 지정됨.

    fun unitPractice(){
        val data1: Unit = Unit;

        fun some(): Unit {
            println("UnitPractice: 20")
        }
    }


    // Nothing - null이나 예외를 반환하는 함수

    // 1. Nothing도 Unit과 마찬가지로 의미 있는 데이터가 아닌 특수한 상황을 표현.
    // 2. Nothing으로 선언한 변수에는 null만 대입할 수 있음. Nothing으로 선언한 변수는 데이터로서의 의미는 없음

    fun nothingPractice(){
        val data1: Nothing? = null
    }

    // 3. 주로 함수 반환 타입을 표현할때 사용. 항상 null만 반환하거나 예외던지는 함수에서 사용

    fun some1(): Nothing? {
        return null
    }

    fun some2(): Nothing {
        throw Exception()
    }

    // 널 허용과 불허용

    // 1. 코틀린의 모든타입은 객체이므로 변수에 null을 대입가능함. 그래서 어떤 변수에서 null이 가능/불가능을 설정해줘야함.
    // 1-1. 그걸 이제 ? 를 이용해서 사용함

    fun nullAcceptOrNotPractice(){
        var data1: Int = 10 // null 가능여부 표현안했으므로 null불가로 디폴트설정
        // data1 = null // 오류

        var data2: Int? = 10 // Int? 를 이용해 값이 null이 될 수 있는것을 명시적으로 선언
        data2 = null // 허용
    }

    // =================================================================
    // 3. 함수 선언하기
    // =================================================================


    // 함수 선언하기

    // 1. fun 키워드로 선언
    // 2. 함수의 매개변수는 var, val 키워드를 사용할 수 없음. 자동으로 val로 적용되며 매개변수값 변경불가
    fun some(data1: Int){
        // data1 = 20 // 자동으로 val로 적용되므로 매개변수값의 변경이 불가능하다.
    }

    // 함수 기본값설정

    // 1. 어떤 함수의 매개변수가 여러 개면 호출할 때 전달한 인자를 순서대로 할당.

    fun some(data1: Int, data2: Int = 10): Int {
        return data1 * data2
    }

    println(some(10))
    println(some(10,20))

    // 1-1. But, 호출할 때 매개변수명을 지정하면 매개변수의 값의 순서를 바꿔도 상관없음.
    some(data2 = 20, data1 = 10)

    // =================================================================
    // 4. 컬렉션 타입
    // =================================================================

    // Array

    // 1. Array 클래스로 표현하며, 첫번째 매개변수(=배열의 크기), 두번째 매개변수(=초깃값)
    // [참고] Array 클래스의 생성쟈 -> <init>(size: Int, init: (Int) -> T)

    // 선언예시
    fun arrayPractice(){
        val data1: Array<Int> = Array(3, {0}) // 0으로 초기화한 크기3짜리 배열 생성
        data1[0] = 10
        data1[1] = 20
        data1.set(2, 30) // 인데긋가 아닌 setter 메소드로 접근
        data1.get(1)     // 인덱스가 아닌 getter 메소드로 접근
    }

    // 기초 타입의 배열

    // 1. 배열의 데이터가 기초 타입이라면 Array를 사용하지 않고 각 기초 타입의 배열을 나타내는 클래스를 이용할 수 있다.
    // 1-1. BooleanArray, ByteArray, CharArray, DoubleArray, FloatArray, IntArray, LongArray, ShortArray 클래스를 이용할 수 있다.

    fun variousArrayPractice(){
        val data1: IntArray = IntArray(3,{0})
        val data2: BooleanArray = BooleanArray(3, {false})

        val data3 = arrayOf<Int>(10,20,30)

        val data4 = intArrayOf(10,20,30)
        val data5 = booleanArrayOf(true,false,true)
    }

    // 2. Array<T> 처럼 제네릭이 아닌 arrayOf로 다른 기본형들도 선언이 가능함.
    // 2-1. booleanArrayOf(), byteArrayOf(), charArrayOf(), doubleArrayOf(), floatArrayOf(), intArrayOf(), longArrayOf(), shortArrayOf() 이용가능

    // List(순서O, 중복O), Set(순서X, 중복X), Map(순서X, 중복X)

    // Collection 타입의 클래스는 가변(Mutable)/불변(immutable) 클래스로 나뉨.

    // List
    // [불변] listOf() | [가변] mutableListOf()
    // Set
    // [불변] setOf() | [가변] mutableSetOf()
    // Map
    // [불변] mapOf() | [가변] mutableMapOf()

    fun listSetMapPractice(){
        var list = listOf<Int>(10,20,30)

        var mutableList = mutableListOf<Int>(10,20,30)
        mutableList.add(3,40)
        mutableList.set(0,50)

        var map = mapOf<String, String>(Pair("one", "hello"), "two" to "world")
        // Map 에서 키-값을 선언하는 방식 두가지
        // 1. Pair("one", "hello") 처럼 Pair 이용해서 선언
        // 2. "two" to "world" 처럼 "문자열" to "문자열" 의 형태로 선언
    }


    // =================================================================
    // 5. 조건문과 반복문
    // =================================================================

    // 조건문 if~else와 표현식
    // 예)

    fun condPractice(){
        var data = 10
        if(data > 10){
            println("data > 10")
        }else if (data > 0 && data <=10){
            println("data > 0 && data <= 10")
        }else{
            println("data <= 0")
        }
    }

    fun condPractice2(){
        var data = 10
        val result = if(data > 10){
            println("data > 10")
            true
        }else if (data > 0 && data <=10){
            println("data > 0 && data <= 10")
            true
        }else{
            println("data <= 0")
            false
        }
        println(result)
    }


    // 조건문 when

    // if~else문 말고 when으로도 조건문을 작성하는 것이 가능하다

    fun whenPractice(){
        var data = 10
        when (data) {
            10 -> println("data is 10")
            20 -> println("data is 10")
            else -> {
                println("data is not valid data")
            }
        }
    }

    fun whenPractice2(){
        var data = "hello"
        when (data) {
            "hello" -> println("data is hello")
            "world" -> println("data is world")
            else -> {
                println("data is not valid data")
            }
        }
    }

    // 다양한 유형의 조건 제시
    fun whenPractice3(){
        var data: Any = 10
        when (data) {
            is String -> println("data is String") // data가 문자열 타입이면
            20,30 -> println("data is 20 or 30")   // data가 20 이거나 30 인 경우
            in 1..10 -> println("data is 1..10") // data가 1~10의 값 인 경우
            else -> { println("data is not valid data")
            }
        }
    }

    // is - 타입을 확인하는 연산자
    // in - 범위나 조건안에 있는지 확인하는 연산자

    // when에서 데이터를 명시하지 않는 경우
    fun whenPractice4(){
        var data = 10
        when{
            data <= 0-> println("data is <= 0") // data가 문자열 타입이면
            data > 100 -> println("data is > 100")   // data가 20 이거나 30 인 경우
            else -> { println("data is valid")
            }
        }
    }

    // 표현식으로 사용가능
    fun whenPractice5(){
        var data = 10
        val result = when{
            data <= 0-> println("data is <= 0") // data가 문자열 타입이면
            data > 100 -> println("data is > 100")   // data가 20 이거나 30 인 경우
            else -> { println("data is valid")
            }
        }
    }

    // 반복문 for와 while

    fun forPractice(){
        var sum: Int = 0
        for (i in 1..10) {
            sum += i
        }
        println(sum)
    }

    // for 문의 다양한 조건

    for (i in 1..10){ }        // 1부터 10까지 1씩 증가
    for (i in 1 until 10){ }   // 1부터 9까지 1씩 증가(10은 미포함)
    for (i in 2..10 step 2){ } // 2부터 10까지 2씩 증가
    for (i in 10 downTo 11){ } // 10부터 1까지 1씩 감소

    // for 문 증감조건을 데이터 개수만큼으로 -> indices 이용

    fun forPractice2(){
        var data = arrayOf<Int>(10,20,30)
        for (i in data.indices) {
            print(data[i])
            if (i !== data.size -1) print(",")
        }
    }

    // 배열의 크기만큼 반복 && 인덱스 가져오기

    fun forPractice3(){
        var data = arrayOf<Int>(10,20,30)
        for ((index, value) in data.withIndex()) {
            print(value)
            if (index !== data.size -1) print(",")
        }
    }

    // while을 이용한 반복문 작성  - 조건이 참이면 중괄호영역 반복 실행 = 조건 거짓이면 break
    fun forPractice4(){
        var x = 0
        var sum1 = 0
        while(x<10) {
            sum1 += ++x
        }
        println(sum1)
    }




















}


