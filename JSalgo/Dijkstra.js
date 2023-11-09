// 가중 그래프 > 다익스트라

const PriorityQueue = require('./PriorityQueue');

class WeightedGraph {
    constructor() {
        this.adjacencyList = [];
    }

    addVertex(vertex) {
        if(!this.adjacencyList[vertex]) this.adjacencyList[vertex] = [];
    }

    addEdge(vertex1, vertex2, weight) {
        this.adjacencyList[vertex1].push({ node: vertex2, weight });
        this.adjacencyList[vertex2].push({ node: vertex1, weight });
    }

    Dijkstra(start, finish) {
        const nodes = new PriorityQueue();
        nodes.enqueue(start, 0);
        
        // distances : 시작점 ~ 노드까지의 최단 거리 기록
        const distances = {};
        // previous : 노드까지 최단거리로 경유해온 직전 노드 기록
        const previous = {};
        
        const path = [];
        let smallest;

        // 아래와 같은 상태로 만들기 위해 for문
        // distances -> { A: 0, B: Infinity, C: Infinity, D: Infinity, E: Infinity, F: Infinity }
        // previous -> { A: null, B: null, C: null, D: null, E: null, F: null }
        for(const vertex in this.adjacencyList) {
            if(vertex === start) {
                distances[vertex] = 0;
            } else {
                distances[vertex] = Infinity;
            }
            previous[vertex] = null;
        }

        while(true) {
            // nodes 내부에는 우선순위 정렬이 되어 있어 dequeue 시 우선 순위가 가장 높은 값(최소 거리)을 줌
            // 그 값의 노드명을 smallest 변수에 재할당
            smallest = nodes.dequeue().val;

            // dequeue한 값이 finish와 같으면 목적지 도착
            if(smallest === finish) {
                // return할 값(path) 만들기
                while(previous[smallest]) {
                    path.push(smallest);
                    smallest = previous[smallest];
                }
                break;
            } else {  // dequeue한 값이 finish와 같지 않으면
                // dequeue한 노드와 간선으로 이어진 노드들에 대해 반복문
                for(const neighbor in this.adjacencyList[smallest]) {
                    const nextNode = this.adjacencyList[smallest][neighbor];

                    // distances[smallest] : 시작점에서 현 노드까지 경유한 거리
                    // nextNode.weight : 현 노드와 다음 노드 사이의 거리
                    // 둘을 합해 최소거리 후보로 둠
                    const candidate = distances[smallest] + nextNode.weight;
                    const nextNeighbor = nextNode.node;

                    // 후보랑 기존에 저장된 거리 비교해서 후보가 더 작으면
                    if(candidate < distances[nextNeighbor]) {
                        // 후보를 새로운 최소 거리로 업데이트
                        distances[nextNeighbor] = candidate;
                        // 다음 노드로 가기 위해 직전에 들린 노드 기록
                        previous[nextNeighbor] = smallest;
                        // 다음 노드와 그 최소 거리(후보 = priority)를 nodes에 넣음
                        nodes.enqueue(nextNeighbor, candidate);
                    }
                }
            }
        }
        return path.concat(smallest).reverse();
    }
}

module.exports = WeightedGraph;