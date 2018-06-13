# light-expr
解析表达式抽象语法树

# light-expr
解析表达式抽象语法树
<pre>
x = 1 + 2 ==>  = [ x , + < 1 , 2 > ]
x = 1 - 2 ==>  = [ x , - < 1 , 2 > ]
x = 1 * 2 ==>  = [ x , * < 1 , 2 > ]
x = 1 / 2 ==>  = [ x , / < 1 , 2 > ]
x = 1 / 2 + 3 ==>  = [ x , + < / < 1 , 2 > , 3 > ]
x = 1 / 2 - 3 ==>  = [ x , - < / < 1 , 2 > , 3 > ]
y = 1*2*3 ==>  = [ y , * < * < 1 , 2 > , 3 > ]
y = 1*2*3*4 ==>  = [ y , * < * < * < 1 , 2 > , 3 > , 4 > ]
y = 1*2*3*4*5 ==>  = [ y , * < * < * < * < 1 , 2 > , 3 > , 4 > , 5 > ]
y = 1*2*3*4*5 + 1*2*3*4 ==>  = [ y , + < * < * < * < * < 1 , 2 > , 3 > , 4 > , 5 > , * < * < * < 1 , 2 > , 3 > , 4 > > ]
x = 1 + 2 * 3 ==>  = [ x , + < 1 , * < 2 , 3 > > ]
x = 1 * 2 + 3 * 4 + 5 * 6 ==>  = [ x , + < + < * < 1 , 2 > , * < 3 , 4 > > , * < 5 , 6 > > ]
x = 1 * 2 + 3 * 4 * 5 ==>  = [ x , + < * < 1 , 2 > , * < * < 3 , 4 > , 5 > > ]
x = 1 - 2 ==>  = [ x , - < 1 , 2 > ]
x = 1 - 2 * 3 ==>  = [ x , - < 1 , * < 2 , 3 > > ]
x = 1 - 2 - 3 ==>  = [ x , - < - < 1 , 2 > , 3 > ]
x = 1 * 2 - 3 ==>  = [ x , - < * < 1 , 2 > , 3 > ]
x = 1 * 2 - 3 * 4 ==>  = [ x , - < * < 1 , 2 > , * < 3 , 4 > > ]
x = 1 * 2 - 3 * 4 - 5 ==>  = [ x , - < - < * < 1 , 2 > , * < 3 , 4 > > , 5 > ]
x = 1 * 2 + 3 * 4 * 5 ==>  = [ x , + < * < 1 , 2 > , * < * < 3 , 4 > , 5 > > ]
x = 1 * 2 + 3 * 4 * 5 + 6 ==>  = [ x , + < + < * < 1 , 2 > , * < * < 3 , 4 > , 5 > > , 6 > ]
x = (1 + 2) * 3 ==>  = [ x , * < + < 1 , 2 > , 3 > ]
x = (1 + 2) * (3 + 4) ==>  = [ x , * < + < 1 , 2 > , + < 3 , 4 > > ]
x = sin(1 + 2) * cos(3 + 4) ==>  = [ x , * < sin < + < 1 , 2 > > , cos < + < 3 , 4 > > > ]
y = sin(x) ==>  = [ y , sin < x > ]
y = sin(x+1) ==>  = [ y , sin < + < x , 1 > > ]
y = sin(x+1) + 2 ==>  = [ y , + < sin < + < x , 1 > > , 2 > ]
y = sin(x+1) + 2*3 ==>  = [ y , + < sin < + < x , 1 > > , * < 2 , 3 > > ]
y = sin(x+1) + 2*3 ==>  = [ y , + < sin < + < x , 1 > > , * < 2 , 3 > > ]
y = sin(x+1) + cos(2*3) ==>  = [ y , + < sin < + < x , 1 > > , cos < * < 2 , 3 > > > ]
y = sin(x+1) ==>  = [ y , sin < + < x , 1 > > ]
y = sin(sin(x)) ==>  = [ y , sin < sin < x > > ]
y = sin(a + cos(b)) ==>  = [ y , sin < + < a , cos < b > > > ]
y = a + sin(b) ==>  = [ y , + < a , sin < b > > ]
y = a * sin(b) ==>  = [ y , * < a , sin < b > > ]
y = a / sin(b) ==>  = [ y , / < a , sin < b > > ]
y = cos(sin(x)) ==>  = [ y , cos < sin < x > > ]
y = cos(sin(a) + b*c + sin(d*e)) ==>  = [ y , cos < + < + < sin < a > , * < b , c > > , sin < * < d , e > > > > ]
y = cos(sin(a)*cos(b) + b*c + sin(d*e)) ==>  = [ y , cos < + < + < * < sin < a > , cos < b > > , * < b , c > > , sin < * < d , e > > > > ]
y = 'a' + 'b' ==>  = [ y , + < 'a' , 'b' > ]
y = cos('a' + 'b') ==>  = [ y , cos < + < 'a' , 'b' > > ]
y = cos('a'*12 + 'b') ==>  = [ y , cos < + < * < 'a' , 12 > , 'b' > > ]


a ==> a
obj.x ==> [obj, x]
obj1.f1 + obj2.f2 ==> + < [obj1, f1] , [obj2, f2] >
max(a, b) ==> max < [a, b] >
min(a, b) ==> min < [a, b] >
min(a+b,c) ==> min < [+ < a , b >, c] >
min(a+b,c,d) ==> min < [+ < a , b >, c, d] >
min(a+b,c,d+e) ==> min < [+ < a , b >, c, + < d , e >] >
min(a+b,c,sin(d+e)) ==> min < [+ < a , b >, c, sin < + < d , e > >] >
cos(a+b),c,sin(d+e) ==> [cos < + < a , b > >, c, sin < + < d , e > >]
cos(a+b),obj.f1,sin(d+e) ==> [cos < + < a , b > >, [obj, f1], sin < + < d , e > >]
</pre>
