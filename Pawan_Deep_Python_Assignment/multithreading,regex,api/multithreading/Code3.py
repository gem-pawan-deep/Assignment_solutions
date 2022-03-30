import time
import threading
from threading import *
def square(a):
    for n in a:
        time.sleep(1)
        print(f"Square of {n} ={n * n} ")
def cube(a):
    for n in a:
        time.sleep(1)
        print(f"Cube of {n} ={n * n* n} ")
def computation(square,cube,arr):
    th1 = threading.Thread(target=square, args=(arr,))
    th2 = threading.Thread(target=cube, args=(arr,))
    threadlock = Lock()
    threadlock.acquire()
    th1.start()
    threadlock.release()
    threadlock.acquire()
    th2.start()
    threadlock.release()
def main():
    arr = [1, 2, 3, 4, 5, 6, 7]
    computation(square, cube, arr)
if __name__ == "__main__":
    s = time.perf_counter()
    run_once = 0
    while(1):
        if run_once == 0:
            main()
            run_once = 1
            break;
    print(time.perf_counter() - s)

