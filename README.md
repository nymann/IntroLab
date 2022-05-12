# IntroLab

### Running the game

##### Via make target

```sh
make run
```

##### Via maven

```sh
mvn package -f AsteroidsLibGDX/pom.xml
java -jar bin/AsteroidsLibGDX-1.0-SNAPSHOT.jar
```

### Implemented features

- Added Enemy ship that moves randomly and shoots bullets
- Bullets will automatically be removed when outside the screen area.
- Naive weapon cooldown: (you can only shoot if there's less than X bullets on screen)
- Colored enemy ship, player ship and bullets.
