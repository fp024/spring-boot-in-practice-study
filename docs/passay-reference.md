# Passay - 레퍼런스 매뉴얼

Passay API는 다음 3가지 핵심 구성 요소로 이루어져 있습니다.

1. [`Rule`](https://www.passay.org/javadocs/org/passay/rule/Rule.html) - 하나 이상의 규칙으로 비밀번호 정책 규칙 집합을 정의합니다.
2. [`PasswordValidator`](https://www.passay.org/javadocs/org/passay/PasswordValidator.html) - 비밀번호가 규칙 집합을 만족하는지 검증합니다.
3. [`PasswordGenerator`](https://www.passay.org/javadocs/org/passay/generate/PasswordGenerator.html) - 주어진 규칙 집합을 만족하는 비밀번호를 생성합니다.

# Rule 개요

Rule은 비밀번호 검증과 생성 모두의 기본 구성 단위이며, Passay가 기본 제공하는 규칙 집합을 살펴보는 것이 도움이 됩니다. 규칙은 크게 두 가지 범주로 나뉩니다.

1. Positive match: 비밀번호가 규칙을 만족해야 합니다.
2. Negative match: 비밀번호가 규칙을 만족하면 거부합니다.

아래 섹션에서는 두 범주에서 사용 가능한 규칙을 간단히 설명합니다.

## Positive matching rules

1. [`AllowedCharacterRule`](https://www.passay.org/javadocs/org/passay/rule/AllowedCharacterRule.html) - 비밀번호가 특정 문자 집합의 *모든* 문자를 포함하도록 요구합니다.
2. [`AllowedRegexRule`](https://www.passay.org/javadocs/org/passay/rule/AllowedRegexRule.html) - 비밀번호가 정규식을 만족하도록 요구합니다.
3. [`CharacterCharacteristicsRule`](https://www.passay.org/javadocs/org/passay/rule/CharacterCharacteristicsRule.html) - 비밀번호가 N개의 문자 클래스 중 M개를 포함하도록 요구합니다. 예: 숫자, 대문자, 소문자, 기호 중 4개 중 3개.
4. [`CharacterRule`](https://www.passay.org/javadocs/org/passay/rule/CharacterRule.html) - 비밀번호가 특정 문자 집합(예: 숫자, 대문자, 소문자, 기호)에서 최소 N개 문자를 포함하도록 요구합니다.
5. [`LengthRule`](https://www.passay.org/javadocs/org/passay/rule/LengthRule.html) - 비밀번호가 최소 길이 조건을 충족하도록 요구합니다.
6. [`LengthComplexityRule`](https://www.passay.org/javadocs/org/passay/rule/LengthComplexityRule.html) - 비밀번호 길이에 따라 특정 규칙 집합을 충족하도록 요구합니다. 예를 들어 길이가 8-12자인 비밀번호는 숫자와 기호를 모두 포함해야 하고, 13자 이상은 알파벳 문자만 포함해야 합니다.

## Negative matching rules

1. Dictionary rules
  1. [`DictionaryRule`](https://www.passay.org/javadocs/org/passay/rule/DictionaryRule.html) - 사전 항목과 *일치*하는 비밀번호를 거부합니다(정확 일치).
  2. [`DictionarySubstringRule`](https://www.passay.org/javadocs/org/passay/rule/DictionarySubstringRule.html) - 사전 항목을 *포함*하는 비밀번호를 거부합니다(부분 문자열 일치).
  3. [`DigestDictionaryRule`](https://www.passay.org/javadocs/org/passay/rule/DigestDictionaryRule.html) - 사전의 다이제스트 항목과 *일치*하는 비밀번호를 거부합니다(해시/다이제스트 비교).
2. History rules
  1. [`HistoryRule`](https://www.passay.org/javadocs/org/passay/rule/HistoryRule.html) - 이전 비밀번호와 일치하는 비밀번호를 거부합니다(평문 비교).
  2. [`DigestHistoryRule`](https://www.passay.org/javadocs/org/passay/rule/DigestHistoryRule.html) - 이전 비밀번호 다이제스트와 일치하는 비밀번호를 거부합니다(해시/다이제스트 비교).
3. [`CharacterOccurrencesRule`](https://www.passay.org/javadocs/org/passay/rule/CharacterOccurrencesRule.html) - 동일 문자가 너무 많이 등장하는 비밀번호를 거부합니다.
4. [`IllegalCharacterRule`](https://www.passay.org/javadocs/org/passay/rule/IllegalCharacterRule.html) - 특정 문자 집합 중 *하나라도* 포함된 비밀번호를 거부합니다.
5. [`IllegalRegexRule`](https://www.passay.org/javadocs/org/passay/rule/IllegalRegexRule.html) - 정규식을 만족하는 비밀번호를 거부합니다.
6. [`IllegalSequenceRule`](https://www.passay.org/javadocs/org/passay/rule/IllegalSequenceRule.html) - N글자 연속 시퀀스(예: *12345*)를 포함하는 비밀번호를 거부합니다.
7. [`NumberRangeRule`](https://www.passay.org/javadocs/org/passay/rule/NumberRangeRule.html) - 정의된 범위의 숫자(예: *1000-9999*)를 포함하는 비밀번호를 거부합니다.
8. Source rules
  1. [`SourceRule`](https://www.passay.org/javadocs/org/passay/rule/SourceRule.html) - 다른 소스의 값과 일치하는 비밀번호를 거부합니다(평문 비교).
  2. [`DigestSourceRule`](https://www.passay.org/javadocs/org/passay/rule/DigestSourceRule.html) - 다른 소스 값의 다이제스트와 일치하는 비밀번호를 거부합니다(해시/다이제스트 비교).
9. [`RepeatCharactersRule`](https://www.passay.org/javadocs/org/passay/rule/RepeatCharactersRule.html) - 반복 문자 시퀀스가 여러 번 나타나는 비밀번호를 거부합니다.
10. [`UsernameRule`](https://www.passay.org/javadocs/org/passay/rule/UsernameRule.html) - 비밀번호 입력 사용자의 사용자명을 포함하는 비밀번호를 거부합니다.
11. [`WhitespaceRule`](https://www.passay.org/javadocs/org/passay/rule/WhitespaceRule.html) - 공백 문자를 포함하는 비밀번호를 거부합니다.

# 비밀번호 검증

비밀번호 검증은 규칙 집합으로부터 `PasswordValidator`를 만드는 과정이며, 규칙 집합은 단순히 `Rule` 객체 목록입니다. 다음과 같은 간단한 비밀번호 정책을 예로 들어 보겠습니다.

1. 길이 8~16자
2. 대문자, 소문자, 숫자, 기호 중 각각 최소 1개 이상 포함
3. 공백 문자 없음

아래 코드 예시는 위 정책을 강제하는 validator를 구성합니다.

```java
PasswordValidator validator = new DefaultPasswordValidator(
  // length between 8 and 16 characters
  new LengthRule(8, 16),

  // at least one upper-case character
  new CharacterRule(EnglishCharacterData.UpperCase, 1),

  // at least one lower-case character
  new CharacterRule(EnglishCharacterData.LowerCase, 1),

  // at least one digit character
  new CharacterRule(EnglishCharacterData.Digit, 1),

  // at least one symbol (special character)
  new CharacterRule(EnglishCharacterData.Special, 1),

  // define some illegal sequences that will fail when >= 5 chars long
  // alphabetical is of the form 'abcde', numerical is '34567', qwery is 'asdfg'
  // the false parameter indicates that wrapped sequences are valid; e.g. 'xyzabc'
  new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 5, false),
  new IllegalSequenceRule(EnglishSequenceData.Numerical, 5, false),
  new IllegalSequenceRule(EnglishSequenceData.USQwerty, 5, false),

  // no whitespace
  new WhitespaceRule());

final char[] password = System.console().readPassword("Password: ");
ValidationResult result = validator.validate(new PasswordData(new UnicodeString(password)));
if (result.isValid()) {
  System.out.println("Password is valid");
} else {
  System.out.println("Invalid password:");
  for (String msg : result.getMessages()) {
    System.out.println(msg);
  }
}
```

## 고급 검증: 메시지 커스터마이징

Passay는 [`MessageResolver`](https://www.passay.org/javadocs/org/passay/resolver/MessageResolver.html) 인터페이스를 제공하여, 비밀번호 검증 결과를 사용자에게 보여줄 의미 있는 텍스트로 자유롭게 변환할 수 있게 합니다. 기본 메커니즘은 메시지 번들을 사용해 검증 메시지를 정의하며, 기본값은 아래와 같습니다.

```properties
HISTORY_VIOLATION=Password matches one of %1$s previous passwords.
ILLEGAL_WORD=Password contains the dictionary word '%1$s'.
ILLEGAL_WORD_REVERSED=Password contains the reversed dictionary word '%1$s'.
ILLEGAL_DIGEST_WORD=Password contains a dictionary word.
ILLEGAL_DIGEST_WORD_REVERSED=Password contains a reversed dictionary word.
ILLEGAL_MATCH=Password matches the illegal pattern '%1$s'.
ALLOWED_MATCH=Password must match pattern '%1$s'.
ILLEGAL_CHAR=Password %2$s the illegal character '%1$s'.
ALLOWED_CHAR=Password %2$s the illegal character '%1$s'.
ILLEGAL_QWERTY_SEQUENCE=Password contains the illegal QWERTY sequence '%1$s'.
ILLEGAL_ALPHABETICAL_SEQUENCE=Password contains the illegal alphabetical sequence '%1$s'.
ILLEGAL_NUMERICAL_SEQUENCE=Password contains the illegal numerical sequence '%1$s'.
ILLEGAL_USERNAME=Password %2$s the user id '%1$s'.
ILLEGAL_USERNAME_REVERSED=Password %2$s the user id '%1$s' in reverse.
ILLEGAL_WHITESPACE=Password %2$s a whitespace character.
ILLEGAL_NUMBER_RANGE=Password %2$s the number '%1$s'.
ILLEGAL_REPEATED_CHARS=Password contains %3$s sequences of %1$s or more repeated characters, but only %2$s allowed: %4$s.
INSUFFICIENT_UPPERCASE=Password must contain %1$s or more uppercase characters.
INSUFFICIENT_LOWERCASE=Password must contain %1$s or more lowercase characters.
INSUFFICIENT_ALPHABETICAL=Password must contain %1$s or more alphabetical characters.
INSUFFICIENT_DIGIT=Password must contain %1$s or more digit characters.
INSUFFICIENT_SPECIAL=Password must contain %1$s or more special characters.
INSUFFICIENT_SPECIAL_ASCII=Password must contain %1$s or more special characters.
INSUFFICIENT_SPECIAL_UNICODE=Password must contain %1$s or more special characters.
INSUFFICIENT_SPECIAL_LATIN=Password must contain %1$s or more special characters.
INSUFFICIENT_CHARACTERISTICS=Password matches %1$s of %3$s character rules, but %2$s are required.
INSUFFICIENT_COMPLEXITY=Password meets %2$s complexity rules, but %3$s are required.
INSUFFICIENT_COMPLEXITY_RULES=No rules have been configured for a password of length %1$s.
SOURCE_VIOLATION=Password cannot be the same as your %1$s password.
TOO_LONG=Password must be no more than %2$s characters in length.
TOO_SHORT=Password must be %1$s or more characters in length.
TOO_MANY_OCCURRENCES=Password contains %2$s occurrences of the character '%1$s', but at most %3$s are allowed.
```

다음 예시는 기본 메시지 번들을 사용자 정의/로컬라이즈된 properties 파일로 교체하는 방법을 보여줍니다.

```java
Properties props = new Properties();
props.load(new FileInputStream("/path/to/passay.properties"));
MessageResolver resolver = new PropertiesMessageResolver(props);
PasswordValidator validator = new DefaultPasswordValidator(
  resolver, new LengthRule(8, 16), new WhitespaceRule());
```

## 고급 검증: M of N 규칙

많은 비밀번호 정책에는 *다음 N개 범주 중 최소 M개를 포함해야 한다* 형태의 규칙이 있습니다. [`CharacterCharacteristicsRule`](https://www.passay.org/javadocs/org/passay/CharacterCharacteristicsRule.html) 컴포넌트는 이 요구사항을 지원합니다. 다음 정책을 살펴보겠습니다.

1. 길이 8~16자
2. 대문자, 소문자, 숫자, 기호 중 최소 3개 범주의 문자 포함
3. 공백 문자 없음

이 정책은 아래 코드 예시로 구현할 수 있습니다.

```java
LengthRule r1 = new LengthRule(8, 16);

CharacterCharacteristicsRule r2 = new CharacterCharacteristicsRule(
  // Define M (3 in this case)
  3,
  // Define elements of N (upper, lower, digit, symbol)
  new CharacterRule(EnglishCharacterData.UpperCase, 1),
  new CharacterRule(EnglishCharacterData.LowerCase, 1),
  new CharacterRule(EnglishCharacterData.Digit, 1),
  new CharacterRule(EnglishCharacterData.Special, 1));

WhitespaceRule r3 = new WhitespaceRule();

PasswordValidator validator = new DefaultPasswordValidator(r1, r2, r3);
```

## 고급 검증: 사전(Dictionary) 규칙

많은 비밀번호 정책은 흔한 단어(예: *password*)가 비밀번호에 포함되지 않도록 합니다. Passay는 임의의 단어 목록과 함께 사용할 수 있는 다음 두 가지 규칙을 제공합니다.

1. [`DictionaryRule`](https://www.passay.org/javadocs/org/passay/DictionaryRule.html) - 정확 일치 방식
2. [`DictionarySubstringRule`](https://www.passay.org/javadocs/org/passay/DictionarySubstringRule.html) - 포함(부분 문자열) 일치 방식

`DictionarySubstringRule`은 신중하게 사용해야 합니다. 흔한 단어 목록이 충분히 큰 경우 [*correcthorsebatterystaple*](http://xkcd.com/936/), *random.words@31415* 같은 강한 비밀번호도 차단될 수 있기 때문입니다. 현실적인 사용 사례는, 비밀번호에 포함이 금지된 예약어의 비교적 짧은 목록을 적용하는 것입니다.

반면 `DictionaryRule`에는 매우 유용한 일반적 사용 사례가 있습니다. 바로 이미 알려진 약한 비밀번호를 막는 것입니다. [Adobe 유출](http://stricture-group.com/files/adobe-top100.txt) 같은 공개된 인기 비밀번호 목록으로 이 규칙을 구성하면, 흔하고 따라서 취약한 비밀번호를 차단해 비밀번호 보안을 크게 높일 수 있습니다.

```java
DictionaryRule rule = new DictionaryRule(
  new WordListDictionary(WordLists.createFromReader(
    // Reader around the word list file
    new FileReader[] {new FileReader("path/to/top100.txt")},
    // True for case sensitivity, false otherwise
    false,
    // Dictionaries must be sorted
    new ArraysSort())));
```

### 제공되는 dictionary 구현

1. [`JDBCDictionary`](https://www.passay.org/javadocs/org/passay/dictionary/JDBCDictionary.html) - 데이터베이스에서 단어를 검색합니다.
2. [`WordListDictionary`](https://www.passay.org/javadocs/org/passay/dictionary/WordListDictionary.html) - 문자열 목록에서 단어를 검색합니다.
  1. [`ArrayWordList`](https://www.passay.org/javadocs/org/passay/dictionary/ArrayWordList.html) - 메모리 내 단어 목록
  2. [`FileWordList`](https://www.passay.org/javadocs/org/passay/dictionary/FileWordList.html) - 파일 기반 단어 목록(작은 파일 크기에 적합)
  3. [`MemoryMappedFileWordList`](https://www.passay.org/javadocs/org/passay/dictionary/MemoryMappedFileWordList.html) - `MappedByteBuffer`를 활용하는 파일 기반 단어 목록(큰 파일 크기에 적합)
3. [`BloomFilterDictionary`](https://www.passay.org/javadocs/org/passay/dictionary/BloomFilterDictionary.html) - [Bloom Filter](https://en.wikipedia.org/wiki/Bloom_filter)에서 단어를 검색합니다.
  1. [Google Guava](https://github.com/google/guava) 라이브러리의 `BloomFilter` 구현을 사용합니다. Bloom filter는 특성상 false positive를 보고하므로, 적절한 false positive 확률을 고려해 필터를 구성해야 합니다.
4. [`TernaryTreeDictionary`](https://www.passay.org/javadocs/org/passay/dictionary/TernaryTreeDictionary.html) - 메모리 내 트리 구조에서 단어를 검색합니다.

## 고급 검증: 비밀번호 이력

다음 규칙들은 비밀번호 이력 기반으로 비밀번호 중복 사용을 방지하는 데 사용됩니다.

1. [`HistoryRule`](https://www.passay.org/javadocs/org/passay/HistoryRule.html) - 평문으로 저장된 비밀번호용(비보안, 드문 방식)
2. [`DigestHistoryRule`](https://www.passay.org/javadocs/org/passay/HistoryRule.html) - 해시/다이제스트로 저장된 비밀번호용

두 규칙 모두 과거 비밀번호 데이터를 위해 데이터 소스를 조회해야 합니다. 하지만 실제로는 비밀번호가 보통 해시/다이제스트로 저장되므로 `DigestHistoryRule`이 더 유용합니다. 다이제스트 지원에는 [cryptacular](http://www.cryptacular.org/) 라이브러리가 제공하는 message digest 컴포넌트가 필요하며, *passay-crypt* Maven 모듈 의존성을 추가하면 사용할 수 있습니다. 아래 예시는 다음 형식으로 저장된 비밀번호를 이력 기반으로 검증하는 방법을 보여줍니다.

1. SHA-256 다이제스트 알고리즘
2. 해시는 두 값을 순서대로 다이제스트하여 계산
  1. UTF-8 문자로 표현한 비밀번호 문자열
  2. 무작위 16바이트 salt 값
3. salt를 해시 출력 뒤에 붙여 48바이트 값 구성(32바이트 해시 + 16바이트 salt)
4. 해시 출력은 base64 문자로 인코딩

이는 LDAP 디렉터리에 SSHA 유사 표준을 사용해 비밀번호를 저장하는 현실적인 시나리오입니다.

```java
// The historical data would be obtained from an authentication store in a
// real-world scenario. Each item consists of a label and the encoded password
// data. A common use case for labels is multiple password encodings where each
// label identifies a particular encoding.
// Salt=86ffd2e3521b5b169ec9a75678c92eed
List<Reference> history = Arrays.asList(
  // Password=P@ssword1
  new HistoricalReference(
    "SHA256",
    "j93vuQDT5ZpZ5L9FxSfeh87zznS3CM8govlLNHU8GRWG/9LjUhtbFp7Jp1Z4yS7t"),

  // Password=P@ssword2
  new HistoricalReference(
    "SHA256",
    "mhR+BHzcQXt2fOUWCy4f903AHA6LzNYKlSOQ7r9np02G/9LjUhtbFp7Jp1Z4yS7t"),

  // Password=P@ssword3
  new HistoricalReference(
    "SHA256",
    "BDr/pEo1eMmJoeP6gRKh6QMmiGAyGcddvfAHH+VJ05iG/9LjUhtbFp7Jp1Z4yS7t")
);

// Cryptacular components:
// org.cryptacular.bean.EncodingHashBean;
// org.cryptacular.spec.CodecSpec;
// org.cryptacular.spec.DigestSpec;
EncodingHashBean hasher = new EncodingHashBean(
  new CodecSpec("Base64"), // Handles base64 encoding
  new DigestSpec("SHA256"), // Digest algorithm
  1, // Number of hash rounds
  false); // Salted hash == false

List<Rule> rules = Arrays.asList(
  // ...
  // Insert other rules as needed
  // ...
  new DigestHistoryRule(hasher));

PasswordValidator validator = new DefaultPasswordValidator(rules);
PasswordData data = new PasswordData("username", "P@ssword1", history);
ValidationResult result = validator.validate(data);
```

# 비밀번호 생성

비밀번호 생성 API는 기존 규칙 집합을 만족하는 무작위 비밀번호를 만드는 데 사용됩니다. `PasswordGenerator` 클래스는 전달된 규칙 타입에 따라 문자 추가기를 구성한 뒤, 생성된 비밀번호를 해당 규칙으로 검증합니다. 생성 시에는 다음 `Rule` 타입을 확인합니다.

1. `CharacterRule`: 생성 비밀번호에 필요한 문자 클래스 정의
2. `CharacterCharacteristicsRule`: `CharacterRule` 집합 포함
3. `AllowedCharacterRule`: 허용 문자 집합 정의
4. `IllegalCharacterRule`: 금지 문자 집합 정의

아래 예시는 다음 정책을 기준으로 비밀번호를 생성하는 방법을 보여줍니다.

1. 길이 8~16자
2. 대문자, 소문자, 숫자 중 각각 최소 1개 이상 포함
3. 공백 문자 없음

```java
List<CharacterRule> rules = Arrays.asList(
  // at least one upper-case character
  new CharacterRule(EnglishCharacterData.UpperCase, 1),

  // at least one lower-case character
  new CharacterRule(EnglishCharacterData.LowerCase, 1),

  // at least one digit character
  new CharacterRule(EnglishCharacterData.Digit, 1));

PasswordGenerator generator = new PasswordGenerator(12, rules);

// Generated password is 12 characters long, which complies with policy
UnicodeString password = generator.generate();
```

위 예시에서 생성된 비밀번호에 공백이 포함되지 않는 이유는, 사용한 어떤 문자 집합에도 공백 문자가 없기 때문입니다. 아래처럼 사용자 정의 문자 집합을 사용하는 `CharacterRule`을 추가하면 공백 포함 비밀번호 생성도 쉽게 지원할 수 있습니다.

```java
new CharacterRule(new CharacterData() {
  @Override
  public String getErrorCode() {
    return "ERR_SPACE";
  }

  @Override
  public String getCharacters() {
    return " ";
  }
}, 1);
```