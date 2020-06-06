echo 
echo "      **************************************************"
echo "      *   Enter the following SQL commands in order:   *"
echo "      *                                                *"
echo "      *   CREATE USER IF NOT EXISTS maxroach;          *"
echo "      *   CREATE DATABASE kotlindb;                    *"
echo "      *   GRANT ALL ON DATABASE kotlindb TO maxroach;  *"
echo "      **************************************************"
echo
cockroach sql --insecure