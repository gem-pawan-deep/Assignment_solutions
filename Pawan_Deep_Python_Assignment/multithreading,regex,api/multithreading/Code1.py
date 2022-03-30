import time
def square(sample):
    start = time.time()
    for i in sample:
        print(f" Square of {i} =  {i * i}  ")
        time.sleep(1)
        t1=time.time()-start
    print("Time for squares: ",t1)
def cube(sample):
    start = time.time()
    for i in sample:
        print(f" Cube of {i} =  {i * i * i}  ")
        time.sleep(1)
    t2=time.time()-start
    print("Time for cubes: ",t2)
sample=[1,2,3,4,5,6,7]
square(sample)
cube(sample)