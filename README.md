Code ka Breakdown:


Student Class:

Yeh class ek student ke data ko represent karti hai. Isme teen attributes hain:
name: 
age: 
hasPaid: 



Filters:

Filters ka kaam specific criteria ko check karna hai. Yeh har ek filter ek condition lagata hai aur decide karta hai ke student process mein agay ja sakta hai ya nahi.
Filters:

EligibilityFilter: Dekhta hai ke student ki age 18 ya usse zyada hai ya nahi.
PaymentFilter: Check karta hai ke kya student ne hostel ki fees pay ki hai.
ServiceMailingFilter: Agar eligibility aur payment filters pass ho jayein, toh service notification bhejta hai.
FilterPipeline Class:

Yeh ek pipeline hai jo filters ko sequence mein execute karti hai. Agar koi filter fail kare, toh process wahi ruk jata hai.
Filters ko pipeline mein add karne ke liye addFilter() method hai.
Student ke application ko process karne ke liye processApplication() method hai.
HostelApplicationService:







Yeh service layer hai jo saari processing ko handle karti hai. Yeh har ek student ka application process karta hai aur filters ko sequentially apply karta hai.
HostelController (Main Class):

Yeh entry point hai jahan hum:
Students banate hain (Ali aur Sara).
Filters ko pipeline mein add karte hain.
Students ko process karte hain aur har ek step ka result print hota hai.







Flow ka Example:
Ali:

Age 20 hai (eligible).
Fees pay ki hui hai.
Service notification bheja jata hai.
Sara:

Age 16 hai (not eligible).
Process yahan ruk jata hai aur payment aur service filters check nahi hote.


Core Concepts ():

Pipeline Pattern: Har ek task ko ek sequence mein execute karte hain. Agar koi step fail kare, toh agla step execute nahi hota.
Layered Architecture: Alag layers banayi gayi hain (model, service, controller) jo code ko organized aur scalable banati hain.
