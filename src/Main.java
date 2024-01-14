import Navigator.MyLinkedListNavigator;
import Navigator.MyNavigator;
import Navigator.Route;

import java.util.*;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);
    public static final MyNavigator navigator = new MyLinkedListNavigator();
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в навигатор!");
        fullNavigator();
        while (true){
            printActions();
            int action = Integer.parseInt(scanner.next());
            switch (action){
                case 1->addRoute();
                case 2->removeRoute();
                case 3->getRouteById();
                case 4->getAllRouts();
                case 5->getAllRoutsBetween();
                case 6->getAllFavoriteRouts();
                case 7->getTopFiveRoutes();
                default -> {
                    if (action == 8){
                        System.out.println("Выход из программы.");
                    } else {
                        System.out.println("Такой команды нет.");
                    }
                }
            }
            if (action==8) break;
        }
    }
    public static void printActions(){
        System.out.println(
                "\nВыберите действие\n"+
                "Добавить новый маршрут - 1\n" +
                "Удалить маршрут по id - 2\n" +
                "Получить маршрут по id - 3\n" +
                "Получить все маршруты  - 4\n" +
                "Получить все маршруты от пункта до пункта- 5\n" +
                "Получить избранные маршруты до пункта назначения - 6\n" +
                "Получить топ 5 маршрутов - 7\n" +
                "Выход - 8");
    }
    private static String generateId(){
        int leftLimit = 48; // цифра '0'
        int rightLimit = 122; // буква 'z'
        int targetStringLength = 10;
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString()+navigator.size();
    }
    public static void fullNavigator(){
        navigator.addRoute(new Route(generateId(), 43.3, true, Arrays.asList("A", "B", "C")));
        navigator.addRoute(new Route(generateId(), 53.3, 3, Arrays.asList("A", "B", "F", "C")));
        navigator.addRoute(new Route(generateId(), 23.3, 1, Arrays.asList("A", "Z", "G", "Y", "C")));
        navigator.addRoute(new Route(generateId(), 73.3, 2, Arrays.asList("A", "B", "U", "W", "T", "C")));
        navigator.addRoute(new Route(generateId(), 83.3, true, Arrays.asList("A", "B", "U", "W", "T", "X", "C")));
        navigator.addRoute(new Route(generateId(), 93.3, true, Arrays.asList("A", "B", "U", "W", "T", "X", "L", "C")));
        navigator.addRoute(new Route(generateId(), 43.3, Arrays.asList("A", "B", "C")));
        navigator.addRoute(new Route(generateId(), 23.6, 3, Arrays.asList("D", "E", "F")));
        navigator.addRoute(new Route(generateId(), 25.8, true, Arrays.asList("G", "H", "C")));
        navigator.addRoute(new Route(generateId(), 73.5, Arrays.asList("J", "K", "C")));
        navigator.addRoute(new Route(generateId(), 22.2, 10, Arrays.asList("M", "N", "O")));
        navigator.addRoute(new Route(generateId(), 56.4, true, Arrays.asList("P", "Q", "C")));
    }
    public static void addRoute(){
        System.out.println("Чтобы добавить новый маршрут введите информацию о нём:");
        System.out.println("Введите все пункты на маршруте включая пункт отправления и пункт прибытия\n" +
                "(Каждое название на новой строке по порядку. " +
                "Постарайтесь ввести без ошибок. Когда закончите, введите '1'):");
        List<String> locationPoints = new ArrayList<>();
        while (true){
            String name = scanner.next();
            if (name.equals("1"))
                break;
            locationPoints.add(name);
        }
        int size = locationPoints.size();
        if(size<2){
            System.out.println("маршрут не может состоять из одного пункта, как минимум 2.");
            return;
        }
        else if (locationPoints.get(0).equals(locationPoints.get(size-1))){
            System.out.println("Пункт прибытия не может соответствовать пункту отправления.");
            return;
        }
        System.out.println("Введите расстояние между начальным и конечным пунктом в километрах\n" +
                "(Число с плавающей точкой, постарайтесь ввести без ошибок):");
        double distance = Double.parseDouble(scanner.next());
        if (distance <=0){
            System.out.println("Дистанция не может быть отрицательной или равняться нулю");
            return;
        }
        try {
            Route route = new Route(generateId(), distance, locationPoints);
            navigator.addRoute(route);
            System.out.flush();
            System.out.println("Новый маршрут успешно добавлен.\n" +
                    "Ему присвоен id = " + route.getId());
        }
        catch (Exception e){
            System.out.println("Ошибка добавления");
            System.out.println(e.getMessage());
        }
    }
    public static void removeRoute(){
        System.out.println("Чтобы удалить маршрут, введите его id:\n" +
                "(Если вы его не знаете, попробуйте просмотреть все маршруты (введите '1') и запомнить id нужного,\n" +
                "затем вернитесь сюда и попробуйте ещё раз.)");
        String id = scanner.next();
        if (id.equals("1")){
            getAllRouts();
            return;
        }
        try {
            navigator.removeRoute(id);
            System.out.println("Маршрут успешно удалён");
        }
        catch (Exception e){
            System.out.println("Ошибка удаления");
            System.out.println(e.getMessage());
        }
    }
    public static void getRouteById(){
        System.out.println("Чтобы получить маршрут, введите его id:\n" +
                "(Если вы его не знаете, попробуйте просмотреть все маршруты (введите '1') и запомнить id нужного,\n" +
                "затем вернитесь сюда и попробуйте ещё раз.)");
        String id = scanner.next();
        if (id.equals("1")){
            getAllRouts();
            return;
        }
        try {
            System.out.println(navigator.getRoute(id));
        }
        catch (Exception e){
            System.out.println("Ошибка поиска");
            System.out.println(e.getMessage());
        }
    }
    public static void getAllRouts(){
        navigator.printAllRouts();
    }
    public static void getAllRoutsBetween(){
        System.out.println("Чтобы получить список маршрутов от пункта до пункта\n" +
                "введите их названия по порядку на новой строке: ");
        System.out.println("Название первого пункта: ");
        String start = scanner.next();
        System.out.println("Название второго пункта: ");
        String end = scanner.next();
        if (end.equals(start)){
            System.out.println("Пункт отправления не должен соответствовать пункту прибытия");
            return;
        }
        for (Route r:navigator.searchRoutes(start, end)){
            System.out.println(r);
        }
    }
    public static void getAllFavoriteRouts(){
        System.out.println("Введите пункт назначения, чтобы получить все избранные маршруты до него:");
        String end = scanner.next();
        for (Route r:navigator.getFavoriteRoutes(end)){
            System.out.println(r);
        }
    }
    public static void getTopFiveRoutes(){
        int i = 0;
        for (Route r:navigator.getTopFiveRoutes()){
            if (i==5) break;
            System.out.println(r);
            i++;
        }
    }

}