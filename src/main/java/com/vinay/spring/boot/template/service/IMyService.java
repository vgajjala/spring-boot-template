public interface IMyService {
    Optional<MyModel> getMyModelById(Long modelId);

    Optional<MyModel> createMyModel(MyModel myModel);

    Optional<MyModel> updateMyModel(MyModel myModel);

    Optional<MyModel> deleteMyModel(MyModel myModel);

}