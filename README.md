# Solutions - Swing

Used technologies:
Java, Swing, SpringBoot, Lombok, Spock, Gradle.

## Model View Controller
This Java application using MVC pattern.
View doesn't know about rest of the application. It is responsible only for the view. Component which shows data (e.g. on JTextArea) has to implement rigth interfaces.
```java
@Component
public class DataView extends JPanel implements MainContract.DataView {

    private final JLabel dataLabel;

    private final JTextArea dataTextArea;

    private final StartButtonView startButtonView;

    public DataView(Messages messages, StartButtonView startButtonView){
        dataLabel = new JLabel(messages.get("main.dataLabel"));
        dataTextArea = new JTextArea();
        dataTextArea.setLineWrap(true);
        this.startButtonView = startButtonView;
    }

    @PostConstruct
    private void init(){
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(dataLabel, BorderLayout.NORTH);
        add(dataTextArea, BorderLayout.CENTER);
        add(startButtonView, BorderLayout.SOUTH);
    }

    @Override
    public void setExampleData(String example) {
        dataTextArea.setText(example);
    }

    @Override
    public String getData() {
        return dataTextArea.getText();
    }

}
``` 
Some components implement Observable interface to notify action (e.g. from JButton).
```java
@Component
public class StartButtonView extends JPanel implements MainContract.StartButtonView, Observable {

    private final JButton startButton;

    private Observer observer;

    public StartButtonView(Messages messages){
        startButton = new JButton(messages.get("main.startSolvingButton"));
    }

    @PostConstruct
    private void init(){
        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(startButton);
        startButton.addActionListener(e -> notifyObserver());
    }

    @Override
    public void setStartButtonEnabled(boolean enabled) {
        startButton.setEnabled(enabled);
    }

    @Override
    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    @Override
    public void notifyObserver() {
        observer.update();
    }
}
```
The Controller reacts to events from View, communicates with Model and decide what to show on the View.

## Why Swing?

I decided to use Swing library instead of JavaFX because it needs JVM only, so user have to installed only Java Runtime Environment.
To look application better I used the UIManager.
```java
UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
```

## Why SpringBoot?

I used SpringBoot to simply connect classes with @Autowired by Constructor. This solution is easily testable with mocks.

## Why Lombok?

I always using Lombok. It saves me a lot of code.

## Why Spock?

Are you using JUnit? I tried Spock, I wrote some tests. After that JUnit became inconvenient for me. Unit tests are more readable.
```groovy
class RandomGeneratorToDiceConverterTest extends Specification {

    RandomGeneratorToDiceConverter randomGeneratorToDiceConverter

    def setup(){
        randomGeneratorToDiceConverter = new RandomGeneratorToDiceConverter()
    }

    def "should throw exception when data is not a number"(){
        given:
        def data = "t"

        when:
        randomGeneratorToDiceConverter.convert(data)

        then:
        thrown(DataNotValidException)
    }

    def "should throw exception when data >= 1"(){
        given:
        def data = "1"

        when:
        randomGeneratorToDiceConverter.convert(data)

        then:
        thrown(DataNotValidException)
    }

    def "should throw exception when data < 0"(){
        given:
        def data = "-0.0001"

        when:
        randomGeneratorToDiceConverter.convert(data)

        then:
        thrown(DataNotValidException)
    }

    def "should return value 1 - 6"(){
        expect:
        randomGeneratorToDiceConverter.convert(data) == value

        where:
        data                || value
        "0.59558786964"     || "4"
        "0.861037873663"    || "6"
        "0.385597702116"    || "3"
        "0.246237673331"    || "2"
        "0.808033385314"    || "5"
        "0.0544673665427"   || "1"
        "0"                 || "1"
    }
}
```
Live long and prosper with Spock Framework :-)

## Why Gradle?

Gradle code is more readable than Maven's XML files.
