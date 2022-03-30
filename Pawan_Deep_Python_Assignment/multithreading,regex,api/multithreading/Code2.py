import time
import threading
def square(sample):
    start = time.time()
    for i in sample:
        print(f" Square of {i} =  {i * i}  ")
        time.sleep(1)
    print("Time for squares: ",time.time()-start)
def cube(sample):
    start = time.time()
    for i in sample:
        print(f" Cube of {i} =  {i * i * i}  ")
        time.sleep(1)
    print("Time for cubes: ",time.time()-start)
sample=[1,2,3,4,5,6,7]
th1 = threading.Thread(target=square,args=[sample])
th2 = threading.Thread(target=cube, args=[sample])
th1.start()
th2.start()
th1.join()
th2.join()