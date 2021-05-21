package MyLearningProject.restservice.mathService;

import MyLearningProject.restservice.models.Crop;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class MathServiceImpl {
    public List<Crop> findUnionOf(List<Crop> list1, List<Crop> list2){
        List<Crop> union = Stream.concat(list1.stream(), list2.stream())
                .distinct()
                .collect(Collectors.toList());

        return union;
    }

    public List<Crop >findIntersectionOf(List<Crop>list1, List<Crop> list2){
        List<Crop> intersect = list1.stream()
                .filter(list2::contains)
                .collect(Collectors.toList());
        return intersect;
    }
}
