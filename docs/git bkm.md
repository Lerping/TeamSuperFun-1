## Clone
```
git clone https://github.com/ethanwerner/TeamSuperFun.git
```

## Implement a new feature

1. Update local master to remote.
```
git pull
```

2. Create and checkout a new branch called "myFeature" off of the branch "main".
```
git checkout -b myFeature main
```

3. Now push your changes to the cloud
```
git push origin myFeature
```

4. Write your code now, make sure that you commit often using:
```
git commit -am "This is a sample commit that is short and concise."
```

5. Once your code is done and you want to merge your changes to master, go to github.com and navigate to the branches view. Click "New pull request." Make sure "Able to merge" is checked, if not proceed to step 6, otherwise "Create pull request". Then have other people review your code.

6. WIP