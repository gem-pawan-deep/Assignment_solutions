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
sample = [1,2,3,4,5,6,7]
def shared(lock,s):
    if s == "s":
        lock.acquire()
        square(sample)
        lock.release()
    elif s=='c':
        lock.acquire()
        cube(sample)
        lock.release()
lock = threading.Lock()
t1 = threading.Thread(target=shared, args=[lock,'s'])
t2 = threading.Thread(target=shared, args=[lock,'c'])
t1.start()
t2.start()
t1.join()
t2.join()