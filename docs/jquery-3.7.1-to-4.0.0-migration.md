# jQuery 3.7.1 → 4.0.0 마이그레이션 가이드

jQuery 4.0.0은 메이저 버전 업그레이드로 다수의 Breaking Change가 포함되어 있다.

---

## 1. 삭제된 API

| 삭제된 API                  | 대체 방법                              |
| --------------------------- | -------------------------------------- |
| `jQuery.isFunction()`       | `typeof fn === "function"`             |
| `jQuery.isWindow()`         | `obj !== null && obj === obj.window`   |
| `jQuery.isArray()`          | `Array.isArray()`                      |
| `jQuery.type()`             | `typeof` 또는 `instanceof`            |
| `jQuery.now()`              | `Date.now()`                           |
| `jQuery.trim()`             | `String.prototype.trim()`             |
| `jQuery.proxy()`            | `Function.prototype.bind()`           |
| `jQuery.event.props`        | 직접 이벤트 속성 접근                  |
| `jQuery.event.fixHooks`     | 직접 이벤트 속성 접근                  |
| `.on("ready")` / `.ready()` | `$(function() { })` 패턴만 지원       |

---

## 2. Ajax 관련 변경

- **콜백 순서 변경** — `jQuery.ajax()`의 Deferred가 네이티브 Promise와 호환되도록 변경되어, 콜백 실행 순서가 달라질 수 있다.
- **JSONP 자동 승격 비활성화** — `jsonp: false`가 기본값일 수 있으므로 JSONP 사용 시 명시적으로 확인이 필요하다.

---

## 3. Deferred / Promise 동작 변경

- jQuery Deferred가 **Promises/A+ 호환**으로 변경되었다.
- `.then()` 콜백이 **비동기(microtask)**로 실행된다.
- 기존에 동기적으로 실행되던 `.then()` 체인이 있다면 동작이 달라질 수 있으므로 주의해야 한다.

```javascript
// 3.x: 동기적으로 실행될 수 있었음
$.Deferred().resolve().then(function() {
  console.log("A");
});
console.log("B");
// 3.x 결과: A → B (동기)
// 4.0 결과: B → A (비동기, microtask)
```

---

## 4. 브라우저 지원 축소

- **IE 11 지원 완전 제거** — IE 관련 워크어라운드 코드가 전부 삭제되었다.
- 최소 지원 브라우저: Chrome, Edge, Firefox, Safari의 최신 ESR 이상

---

## 5. CSS / 애니메이션 변경

- **`jQuery.cssNumber`** — 단위 없는 CSS 속성 목록이 정리되었다.
- 커스텀 CSS 속성을 `jQuery.cssNumber`에 추가하여 사용하고 있었다면 확인이 필요하다.

---

## 6. 이벤트 관련 변경

- **`.toggle(handler, handler)`** (이벤트 토글) — 이미 3.x에서 삭제되었지만, 폴리필 플러그인을 사용 중이라면 4.0 호환 여부를 확인해야 한다.
- **포커스 이벤트 순서** — `focus` / `blur` 이벤트가 네이티브 브라우저 동작과 일치하도록 순서가 조정되었다.

---

## 7. 확인 체크리스트

- [ ] 프로젝트 내 JS/HTML 파일에서 삭제된 API 사용 여부 검색
- [ ] `$.ajax()` 사용 패턴 중 Deferred 콜백 타이밍에 의존하는 코드 확인
- [ ] 사용 중인 jQuery 플러그인의 4.0 호환 여부 확인
- [ ] Bootstrap 5.3.x는 jQuery 의존성이 없으므로 직접적인 영향은 없음
- [ ] [jQuery Migrate 플러그인](https://github.com/jquery/jquery-migrate) 을 추가하여 삭제된 API 사용 시 콘솔 경고로 영향 범위 파악

---

## 참고 링크

- [jQuery 4.0.0 릴리즈 블로그](https://blog.jquery.com/2024/02/06/jquery-4-0-0-beta/)
- [jQuery 4.0 Upgrade Guide](https://jquery.com/upgrade-guide/4.0/)
- [jQuery Migrate 플러그인](https://github.com/jquery/jquery-migrate)
