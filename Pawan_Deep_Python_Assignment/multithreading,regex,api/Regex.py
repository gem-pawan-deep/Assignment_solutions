import re
str = ["example (.in)", "w3resource", "github (.com)", "stackoverflow (.us1)"]
for i in str:
    s = re.sub(r" ?\([^)]+\)", "", i)
    print(s)