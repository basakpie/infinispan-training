infinispan-training
===================

# infinispan
Distributed in-memory key/value data grid and cache이며 Apache 2.0 license로 Java, Scala 언어로 구현 되어 있습니다.
추가적인 기능으로 CacheStore(File,DB,RemoteServer,Cassandra,HBase,MongoDB...), SearchQuery(Apache Lucene), Transanction, 
Map/Reduce, Distributed Execution.. 등을 지원 합니다.


# infinispan Mode
* Library : Embeded 형태로 Application에 Library(infinispan-core-xx.jar)를 포함하여 Cache를 관리 하는 형태
* Client/Server : Client(Application) <-> Server(Infinispan) 형태로 서버에 질의 하여 Cache를 관리 하는 형태

# infinispan Server Protocal
* Memcached : 추가/삭제 되는 서버들의 인스턴스 감지 불가 함
* HotRod (Infinispan native protocol) : 추가/삭제 되는 서버들의 인스턴스를 자동으로 감지하고 ReHashing 진행
* Rest : http, websocket을 통한 접근 지원 
 
# infinispan Hotrod Client Lageuage
Java, Ruby, Python, C++, .NET

# infinispan Distributed Topology
* Local Storage - Local Cache 용도로 사용. 노드간 복제 않함
* Replication Cache  - 데이터를 모든 노드에 똑같이 복제 함
* Distributed Cache - 데이터를 노드에 분산 저장 시킴 (장애 발생을 위해 복제 노드 값을 지정 할 수 있음)
* L1 + Disribution
* Invalidation Cache - 데이터가 삭제 될 때 다른 노드에 이벤트를 발생 시킴

# infinispan Evition & Expiration
* Evition : Max Entriy 개수를 넘었을 때 FIFO, LRU... 등에 따라 삭제
* Expiration : 일정 시간이 지나면 삭제

# infinispan DataStore
캐쉬 데이터 영속화를 위한 저장 스토어(File,DB,RemoteServer,Cassandra,HBase,MongoDB...) 연동되어 지며 Application Cache Data, Search를 위한 Apahce Lucene Index Data 두 가지 형태의 데이터 스토어 제공
* Passivation : Cache 이벤트와 함께 동기 or 비동기로 스토어 쓰여짐
* Activation : Cache 지워지거나 서버가 내려 갈 때 동기 or 비동기로 스토어 쓰여짐

# infinispan Etc
* Map/Reduce, Distributed Execution, Transaction, Distribut Search, Async, Sort, Pagenation, Lock 처리 등을 지원 함.

# infinisapn training test guide
* Library Mode
* Client/Server Mode




 











