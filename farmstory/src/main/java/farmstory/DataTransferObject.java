package farmstory;

/**
 * DTO 인터페이스. 모든 DTO는 {@link DataTransferObject}를 implement 해야 함.
 * 
 * 그 이유는, 인터페이스를 사용함으로써 불필요한 메서드에 의존하지 않을 수 있게 되며, 결과적으로 클래스들이 상호 느슨하게 연결된다.
 */
public interface DataTransferObject {
  String toString();
}
