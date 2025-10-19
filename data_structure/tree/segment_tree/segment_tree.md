# Segment Tree
> 세그먼트 트리는 특정 구간 (range)에 대한 질의 (query)를 효율적으로 처리(답변)하기 위한 이진 트리 기반 자료 구조.
>
> 구간 질의 (range quary)를 빠르게 해결한다고 해도됨.

배열의 부분 구간에 대해 `sum`, `min`, `max`, `mean` 등을 빠르게 구할때 사용한다.

> 배열 A = [5, 3, 8, 7, 2, 6] 이 있을 때,
> 
> A[2..5] 구간합을 여러 번 물어본다면?
 
매번 for 돌면 `O(N)`이라, 질의가 많을 땐 `O(NQ)`.

세그먼트 트리는 이를 `O(log N)` 만에 처리한다.
대신 초기 구축(트리 빌드)은 `O(N)`, 갱신(update)도 `O(log N)`.

---
### 틈새 지식
`항등원` : 어떤 원소와 연산을 해서 자기 자신이 나오게 하는 원소 </br>
ex) `+` 의 항등원은 `0`  1 + 0 = 0

`역원` : 어떤 원소와 연산을 해서 항등원을 나오게 하는 원소 </br>
ex) `a` `+` 의 역원은 -a

`누적 합 (prefix sum)` : prefix 는 접두사(단어의 맨 앞에 붙는 부분)라는 뜻. 왜 접두사 합이냐? 첫 번째 원소부터(맨앞) 더해서.

구간 질의를 빠르게 처리하는 방법으론 이런 누적 연산 배열로도 가능한 부분이 있지만 이전 구간 데이터를 무효화하려면 해당 누적 연산이 역원이 있어야함. 예를 들어, 누적 합은 역원이 있어 원하는 구간 - 그 이전 구간으로 특정 구간 합만 뽑을 수 있지만, max, min 과같은 연산이었다면 역원이 없어서 누적 방식으론 불가능.

이를 극복하기 위해, 세그먼트 트리를 사용하는 것도 방법.

---

# 아이디어

트리는 배열을 반으로 쪼개나가며 관리한다.

```
인덱스:  [0  1  2  3  4  5]
값:      5  3  8  7  2  6
```

1. 루트 노드: 전체 구간 [0, 5] 의 합 (즉, 5+3+8+7+2+6 = 31)

2. 왼쪽 자식: [0, 2] 합 = 16

3. 오른쪽 자식: [3, 5] 합 = 15

4. 왼쪽 자식의 왼쪽 자식: [0, 1] 합 = 8

5. 왼쪽 자식의 오른쪽 자식: [2, 2] 합 = 8

6. … 이런 식으로 리프(leaf)가 단일 원소가 될 때까지 내려간다.

이 구조를 배열 형태로 저장하면 (인덱스 1부터 시작)

```
tree[1] = [0,5]
tree[2] = [0,2]   tree[3] = [3,5]
tree[4] = [0,1]   tree[5] = [2,2]   tree[6] = [3,4]   tree[7] = [5,5]
```

각 노드는 자신의 구간 합을 기억한다.

---

# 핵심 연산
아이디어를 가지고 sum query를 저장하는 세그먼트 트리를 만들어보자
## build(트리생성)
```java
void build(int node, int start, int end) {
    if (start == end) {
        tree[node] == arr[start];
        return;
    }
    
    int mid = (start + end) / 2;

    build(node * 2, start, mid);
    build(node * 2 + 1, mid + 1, end);
    tree[node] = tree[node * 2] + tree[node * 2 + 1];
}
```
- 리프 노드는 배열의 실제 값.
- 부모 노드는 자식 둘의 합

## query(구간합 구하기)
```java
int query(int node, int start, int end, int left, int right) {
    if (right < start || end < left) return 0; // out of bounds
    if (left <= start && end <= right) return tree[node]; // fully in bounds

    int mid = (start + end) / 2;
    return query(node * 2, start, mid, left, right) +
           query(node * 2 + 1, mid + 1, end, left, right);
}
```

- 구간 [left, right] 의 합을 구한다.
- 현재 노드의 구간이 질의 구간에 완전히 벗어나면 0 반환
- 현재 노드의 구간이 질의 구간에 완전히 속하면 해당 노드 값 반환
- 그렇지 않으면 자식 노드로 내려가 재귀 호출    
- 시간복잡도: `O(log N)`
- 공간복잡도: `O(N)`
  
## update(값 갱신)
```java
void update(int node, int start, int end, int idx, int diff) {
    if (idx < start || idx > end) return;

    tree[node] += diff;
    
    if (start != end) {
        int mid = (start + end) / 2;
        update(node*2, start, mid, idx, diff);
        update(node*2+1, mid+1, end, idx, diff);
    }
}
```
- 배열의 특정 인덱스 `idx` 의 값을 `diff` 만큼 증가시킨다.
- 현재 노드의 구간이 `idx` 를 포함하지 않으면 종료
- 현재 노드의 값을 `diff` 만큼 갱신
- 리프 노드가 아니면 자식 노드로 내려가 재귀 호출
- 시간복잡도: `O(log N)`
- 공간복잡도: `O(N)`
- `diff` 는 새로운 값 - 기존 값
- 예: arr[2] = 8 에서 10 으로 바꾸려면 diff = 10 - 8 = 2
- update(1, 0, N-1, 2, 2) 호출
- 트리의 모든 관련 노드가 갱신됨
---
# 왜 log N 인가?
한 번의 질의나 갱신에서 트리를 전부 탐색하지 않는다.
한 단계 내려갈 때마다 구간이 절반으로 줄어들기 때문에,
탐색 깊이는 log₂N.

→ N = 1,000,000일 때도 최대 깊이가 약 20 수준이다.

---
# one more step
## Lazy Propagation (지연 갱신)
구간 업데이트가 빈번할 때(예: “구간 [L..R] 에 +3 더하기”)
모든 리프를 다 건드리면 O(N).
그래서 “나중에 갱신하겠다” 표시만 남겨두고,
진짜 필요할 때 내려보내는 게 lazy propagation.

## 세그트리의 일반화
합만 저장할 필요는 없다.

min/max 세그트리

GCD 세그트리

XOR 세그트리

심지어 문자열 길이, boolean OR 도 가능.
결합법칙(associative law) 이 성립하는 연산이면 된다.

---
# 시간 복잡도 요약
| 목표      | 방법               | 시간복잡도    |
| ------- | ---------------- | -------- |
| 구간 합    | 세그먼트 트리          | O(log N) |
| 구간 합 갱신 | update()         | O(log N) |
| 구간 갱신   | lazy propagation | O(log N) |
| 초기 구축   | build()          | O(N)     |
---