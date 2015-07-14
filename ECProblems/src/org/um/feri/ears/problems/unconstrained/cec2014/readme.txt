testfunc.java is the test function
Example:
testfunc tf = new testfunc();

tf.test_func(x, f, dimension,population_size,func_num);

testmain.java is an example function about how to use testfunc.java

-----------------------------------------------------------------------
2014/08/08
Bugs modification:

java code source was modified in 4 places:

1. F23:(line 1285)

fit[i]=10000*fit[i]/1e+5;--->fit[i]=10000*fit[i]/1e+10;


2. F26(line 1420)

fit[i]=1000*fit[i]/1e+3;--->fit[i]=1000*fit[i]/1e+10;

3. F27(line 1498)

fit[i]=ellips_func(x,fit[i],nx,tOs,tMr,1,0);--->fit[i]=ellips_func(x,fit[i],nx,tOs,tMr,1,r_flag);

4. F28(line 1557)

fit[i]=ellips_func(x,fit[i],nx,tOs,tMr,1,0);--->fit[i]=ellips_func(x,fit[i],nx,tOs,tMr,1,r_flag);

to make it be consistent with c and matlab code.